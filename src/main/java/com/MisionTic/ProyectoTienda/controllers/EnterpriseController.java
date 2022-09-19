package com.MisionTic.ProyectoTienda.controllers;
import com.MisionTic.ProyectoTienda.entities.Enterprise;
import com.MisionTic.ProyectoTienda.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/views/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService pruebaService;

    @GetMapping("/")
    public String  listar(Model model){
        List<Enterprise> enterprise = pruebaService.listar();
        model.addAttribute("titulo","Listar Enterprise");
        model.addAttribute("enterprise", enterprise);
        return "views/enterprise/listar";
    }
    @GetMapping("/crear")
    public String crear(Model model){
        Enterprise enterprise = new Enterprise();
        model.addAttribute("titulo","Crear enterprise");
        model.addAttribute("enterprise", enterprise);
        return "views/enterprise/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Enterprise enterprise){
        pruebaService.guardar(enterprise);
        return "redirect:/views/enterprise/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long idEnterprise, Model model){
        Enterprise enterprise = pruebaService.buscarId(idEnterprise);
        model.addAttribute("titulo", "Editar enterprise");
        model.addAttribute("enterprise", enterprise);
        return "views/enterprise/crear";

    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long idEnterprise){
        pruebaService.eliminar(idEnterprise);
        return "redirect:/views/enterprise/";
    }


}

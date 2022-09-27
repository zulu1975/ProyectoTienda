package com.MisionTic.ProyectoTienda.controllers;
import com.MisionTic.ProyectoTienda.Interfaces.IEmployeService;
import com.MisionTic.ProyectoTienda.Interfaces.IEnterpriseService;
import com.MisionTic.ProyectoTienda.Interfaces.IProfileService;
import com.MisionTic.ProyectoTienda.entities.Employe;
import com.MisionTic.ProyectoTienda.entities.Enterprise;
import com.MisionTic.ProyectoTienda.entities.EnumRole;
import com.MisionTic.ProyectoTienda.entities.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("views/employe")
public class EmployeController {

    @Autowired
    private IEmployeService employeService;
    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private IProfileService profileService;

    @GetMapping("/")
    public String listar (Model model){
        List<Employe> employe = employeService.list();
        List<Enterprise> listEnterprise = enterpriseService.listar();//Muestra la empresa
        model.addAttribute("titulo", "Empleados");
        model.addAttribute("employe", employe);
        return "views/employe/listar";
    }

    @GetMapping("/crear")
    public String crear (Model model){
        Employe employe = new Employe();
        List<Enterprise> listEnterprise = enterpriseService.listar();
        List<Profile> listProfile = profileService.lista();
        model.addAttribute("titulo", "Nuevo Empleado");
        model.addAttribute("employe", employe);
        model.addAttribute("enterprise", listEnterprise);
        model.addAttribute("enumRole", EnumRole.values());
        return "views/employe/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Employe employe){
        employeService.guardar(employe);
        return "redirect:/views/employe/";
    }

    @GetMapping("/editar/{id}")
    public String searchById (@PathVariable("id") Long idEmploye, Model model){
        Employe employe = employeService.searchById(idEmploye);
        model.addAttribute("titulo", "Actualizar Empleado");
        model.addAttribute("employe", employe);
        return "views/employe/crear";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar (@PathVariable("id") Long idEmploye){
        employeService.eliminar(idEmploye);
        System.out.println("Registro eliminado con exito");
        return "redirect:/views/employe/";
    }
}
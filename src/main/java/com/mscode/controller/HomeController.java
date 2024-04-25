package com.mscode.controller;

import com.mscode.services.RegimentoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mscode.services.MembrosService;
import com.mscode.services.VideoService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
    @Autowired
    private VideoService videoService;

    @Autowired
    private MembrosService membrosService;

	@Autowired
	RegimentoServices regimentoServices;


	@GetMapping("/home")
	public String home() {

		System.out.println("Sem cache: /home");

		return "hello";
	}
	@GetMapping
	public String index(Model model) {
				
		model.addAttribute("qtdmembros", membrosService.findUniqueRecord());
        model.addAttribute("videos", videoService.listaUltimosVideo());

		System.out.println("Com cache");

		return "index";
	}
	
	@GetMapping("/treinamento")
	public String treinamento() {
		return "treinamento/treinamento";
	}

	@GetMapping("/regimeinterno")
	public String regimeinterno(Model model){

		model.addAttribute("regras", regimentoServices.listaRegras());
		return "regime/regimeinterno";
	}
	
}

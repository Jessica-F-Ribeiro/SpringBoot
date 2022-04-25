package br.senai.sp.hellospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.hellospringboot.model.Animal;
import br.senai.sp.hellospringboot.repository.AnimalRepository;
import br.senai.sp.hellospringboot.repository.ClienteRepository;

@Controller
public class AnimalController {
	@Autowired
	ClienteRepository repCliente;
	@Autowired
	AnimalRepository repAnimal;
	
	@RequestMapping("formAnimal")
	public String form(Model model) {
		model.addAttribute("clientes", repCliente.findAll());
		return "animal/form";
	}
	
	@RequestMapping("salvarAnimal")
	public String salvar(Animal animal) {
		repAnimal.save(animal);
		return "redirect:formAnimal";
	}
}

package br.com.homeoffice.registroescolar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homeoffice.registroescolar.models.Professor;
import br.com.homeoffice.registroescolar.models.StatusProfessor;
import br.com.homeoffice.registroescolar.repositories.ProfessorRepository;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;
	
	
	@GetMapping("/professores")
	private ModelAndView index() {
	
		
		List<Professor> professores = this.professorRepository.findAll();

		ModelAndView mv = new ModelAndView("professores/index");

		mv.addObject("professores", professores);

		return mv;
	}

	@GetMapping("/professor/new")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView("professores/new");
		
		mv.addObject("statusProfessor", StatusProfessor.values());
		
		return mv;
	}
	
}

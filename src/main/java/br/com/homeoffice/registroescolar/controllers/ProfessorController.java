package br.com.homeoffice.registroescolar.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homeoffice.registroescolar.dto.RequisicaoNovoProfessor;
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

	@GetMapping("/professores/new")
	public ModelAndView novo(RequisicaoNovoProfessor requisicao) {
		
		ModelAndView mv = new ModelAndView("professores/new");
		
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		
		return mv;
	}
	
	@PostMapping("/professores")
	public ModelAndView create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
		
			System.out.println("*******  TEM ERROS  **********");
			
			ModelAndView mv = new ModelAndView("professores/new"); 
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			
			return mv;
		
		}else {
			
			Professor professor = requisicao.toProfessor();
			this.professorRepository.save(professor);
			
			return new ModelAndView("redirect:/professores/" + professor.getId());
		
		}

	}
	
	@GetMapping("/professores/{id}")
	public ModelAndView show(@PathVariable Long id) {
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			
			ModelAndView mv = new ModelAndView("professores/show");
			mv.addObject(professor);
			
			return mv;
			
		}else {
			System.out.println("$$$$$$$ NÃO ACHOU O PROFESSOR ID " + id + " $$$$$$$");
			
			return new ModelAndView("redirect:/professores");

		}
		

		
	}
	
}

package br.com.homeoffice.registroescolar.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.homeoffice.registroescolar.dto.RequisicaoFormProfessor;
import br.com.homeoffice.registroescolar.models.Professor;
import br.com.homeoffice.registroescolar.models.StatusProfessor;
import br.com.homeoffice.registroescolar.repositories.ProfessorRepository;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;
	
	
	@GetMapping("")
	private ModelAndView index() {
	
		
		List<Professor> professores = this.professorRepository.findAll();

		ModelAndView mv = new ModelAndView("professores/index");

		mv.addObject("professores", professores);

		return mv;
	}

	@GetMapping("/new")
	public ModelAndView novo(RequisicaoFormProfessor requisicao) {
		
		ModelAndView mv = new ModelAndView("professores/new");
		
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
		
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
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			
			ModelAndView mv = new ModelAndView("professores/show");
			mv.addObject("professor", professor);
			
			return mv;
			
		}else {
			System.out.println("$$$$$$$ NÃO ACHOU O PROFESSOR ID " + id + " $$$$$$$");
			
			return new ModelAndView("redirect:/professores");

		}
		
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao) {
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			
			Professor professor = optional.get();
			
			requisicao.fromProfessor(professor);
			
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("professorId", professor.getId());
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			
			return mv;
		
		}else {
			
			System.out.println("$$$$$$$ NÃO ACHOU O PROFESSOR ID " + id + " $$$$$$$");
			
			return new ModelAndView("redirect:/professores");

		}

	}
	
	
	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("professorId", id);
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			
			return mv;
		
		}else {
			
			Optional<Professor> optional = this.professorRepository.findById(id);
			
			if(optional.isPresent()) {

				Professor professor  = requisicao.updProfessor(optional.get());
				this.professorRepository.save(professor);
				return new ModelAndView("redirect:/professores/" + professor.getId());
				
			}
			else {
				
				System.out.println("$$$$$$$ NÃO ACHOU O PROFESSOR ID " + id + " $$$$$$$");
				return new ModelAndView("redirect:/professores");

			}

		}
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		
		try {
			this.professorRepository.deleteById(id);
			return "redirect:/professores";
		}catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
			return "redirect:/professores";
		}
		
		
		
	}
	
}

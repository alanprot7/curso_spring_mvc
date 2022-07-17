package br.com.homeoffice.registroescolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.homeoffice.registroescolar.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}

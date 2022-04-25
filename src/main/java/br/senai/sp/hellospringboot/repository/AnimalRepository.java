package br.senai.sp.hellospringboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.senai.sp.hellospringboot.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long>{
	public List<Animal> findByClienteId(Long idCliente);
}

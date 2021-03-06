package br.senai.sp.hellospringboot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "VARCHAR(100)")
	private String nome;
	@Column(unique = true, length = 11)
	private String cpf;
	private String email;
	@OneToMany(mappedBy = "cliente")
	private List<Animal> animais;
	
}

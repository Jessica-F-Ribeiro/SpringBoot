package br.senai.sp.hellospringboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senai.sp.hellospringboot.model.Cliente;
import br.senai.sp.hellospringboot.repository.ClienteRepository;

@Controller
public class ClienteController {
	@Autowired
	private ClienteRepository repository;
	
	@RequestMapping(value = "formCliente", method = RequestMethod.GET)
	public String formCliente() {
		return "cliente/form";
	}
	
	@RequestMapping(value="formCliente", method = RequestMethod.POST)
	public String salvarCliente(Cliente cliente) {
		repository.save(cliente);
		return "redirect:formCliente";
	}
	
	@RequestMapping("listaCliente")
	public String listarCliente(Model model) {
		model.addAttribute("clientes", repository.findAll());
		return "cliente/lista";
	}
	
	@RequestMapping("alterarCliente")
	public String alterarCliente(Model model, Long id) {
		Cliente cliente = repository.findById(id).get();
		System.out.println(cliente.getAnimais().size());
		model.addAttribute("cliente", cliente);
		return "forward:formCliente";
	}
	
	@RequestMapping("excluirCliente")
	public String excluirCliente(Long id) {
		repository.deleteById(id);
		return "redirect:listaCliente";
	}

	@RequestMapping("formBusca")
	public String formBusca() {
		return "cliente/formBusca";
	}
	
	@RequestMapping("buscarPorCpf")
	public String buscarPeloCpf(String cpf, Model model, RedirectAttributes att) {
		Cliente cliente = repository.findByCpf(cpf);
		if(cliente == null) {
			att.addFlashAttribute("msg", "Cliente não encontrado");
			return "redirect:formBusca";
		}
		model.addAttribute("cliente", cliente);
		return "forward:formCliente";
	}
	
	@RequestMapping("buscarPorNome")
	public String buscarPeloNome(String nome, Model model) {
		model.addAttribute("clientes", repository.findByNomeLike("%"+nome+"%"));
		//model.addAttribute("clientes", repository.procurarPeloNome(nome));
		return "cliente/lista";
	}
}

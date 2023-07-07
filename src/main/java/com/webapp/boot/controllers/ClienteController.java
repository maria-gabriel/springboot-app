package com.webapp.boot.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webapp.boot.models.entity.Cliente;
import com.webapp.boot.models.service.ClienteServiceInterface;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteServiceInterface clienteService;
	
	@GetMapping("/inicio")
	public String inicio(Model model) {
		model.addAttribute("titulo", "Inicio");
		
		return "inicio";
	}
	
	@GetMapping("/clientes")
	public String clientes(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("clientes", clienteService.findAll(pageRequest));
		
		return "clientes";
	}
	
	@GetMapping("/formulario")
	public String formulario (Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Nuevo Registro");
		model.put("cliente", cliente);
		
		return "formulario";
	}
	
	@GetMapping("/formulario/{id}")
	public String modificar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "No se encontró el registro");
				return "redirect:/clientes";
			}
		} else {
			flash.addFlashAttribute("error", "Identificador no válido");
			return "redirect:/clientes";
		}
		
		model.put("titulo", "Editar Registro");
		model.put("cliente", cliente);
		
		return "formulario";
	}
	
	@PostMapping("/formulario")
	public String insertar(@Valid Cliente cliente, BindingResult result, SessionStatus status, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return "formulario";
		}
		
		String mnsFlash = (cliente.getId() == null ? "Registro guardado con éxito" : "Registro modificado con éxito");
		
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mnsFlash);
		return "redirect:clientes";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if(id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Registro eliminado con éxito");
		}
		return "redirect:/clientes";
	}
	
}

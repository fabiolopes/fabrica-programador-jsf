package br.com.fabricadeprogramador.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fabricadeprogramador.model.Cliente;
import br.com.fabricadeprogramador.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteController {

	private Cliente cliente = new Cliente();

	private List<Cliente> clientes;

	@PostConstruct
	public void init() {
		clientes = repository.buscarTodos();
	}

	@Autowired
	private ClienteRepository repository;

	private boolean modoEdicao = false;

	public void salvar() {
		repository.save(cliente);
		if(!isModoEdicao())
			clientes.add(cliente);			
		cliente = new Cliente();
		setModoEdicao(false);
	}
	
	public void excluir(Cliente c){
		repository.delete(c);
		clientes.remove(c);
	}
	
	public void editar(Cliente cliente){
		setCliente(cliente);
		setModoEdicao(true);
	}
	
	public void cancelar(){
		cliente = new Cliente();
		modoEdicao = false;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public boolean isModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

}

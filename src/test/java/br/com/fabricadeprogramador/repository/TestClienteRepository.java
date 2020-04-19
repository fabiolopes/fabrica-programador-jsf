package br.com.fabricadeprogramador.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fabricadeprogramador.model.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestClienteRepository {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EntityManager manager;
	
	@Test
	public void testSalvar(){
		Cliente cli = new Cliente("Jão", "Jão@htcursos.com");
		Cliente cliSalvo = clienteRepository.save(cli);
		assertNotNull(cliSalvo.getId());
	}
	
	@Test
	public void testBuscarPorEmail(){
		Cliente cli = new Cliente("Jão", "Jão@htcursos.com");
		manager.persist(cli);
		Cliente cliEncontrado = clienteRepository.buscarPorEmail("Jão@htcursos.com");
		assertEquals(cli.getEmail(), cliEncontrado.getEmail());
	}
	
	@Test
	public void testBuscarTodos(){
		Cliente c1 = new Cliente("Jão", "jao@email");
		Cliente c2 = new Cliente("Je", "je@email");
		manager.persist(c1);
		manager.persist(c2);
		
		List<Cliente> cliList = clienteRepository.buscarTodos();
		assertEquals(2, cliList.size());
	}
}

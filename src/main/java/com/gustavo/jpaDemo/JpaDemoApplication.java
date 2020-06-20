package com.gustavo.jpaDemo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gustavo.model.Categorias;
import com.gustavo.model.Perfil;
import com.gustavo.model.Usuario;
import com.gustavo.model.Vacante;
import com.gustavo.repository.CategoriasRepository;
import com.gustavo.repository.PerfilesRepository;
import com.gustavo.repository.UsuariosRepository;
import com.gustavo.repository.VacantesRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.gustavo")
@EntityScan(basePackages = "com.gustavo")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.gustavo")

public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repoCategorias;
	@Autowired
	private VacantesRepository repoVacantes;
	@Autowired
	private PerfilesRepository repoPerfiles;
	@Autowired
	private UsuariosRepository repoUsuarios;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * Metodos con CrudRepository
		 */
		// create();
		// readporID();
		// update();
		// delete();
		// buscarVarios();
		// readAll();
		// count();
		// createAll();
		// existe();

		/**
		 * Metodos con JpaRepository
		 */
		// buscarTodas();
		// buscarTodosOrdenados();
		// buscarTodosPagination();
		// buscarTodosPaginationOrdenados();

		// buscarTodasVacantes();
		// createProfiles();
		// createUser();
		// buscarUser();

		// buscarVacantePorEstatus();
		// buscarVacantePorEstatusDestacado();
		// buscarVacanteSalario();
		buscarVacantesVariosEstatus();
	}

	public void create() {

		Categorias cat = new Categorias();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajo relionado a finanzas");
		repoCategorias.save(cat);
		System.out.println(cat);

	}

	public void createAll() {

		List<Categorias> categorias = getListCategorias();
		repoCategorias.saveAll(categorias);
	}

	public void readporID() {
		Optional<Categorias> opcional = repoCategorias.findById(1);

		if (opcional.isPresent()) {
			System.out.println(opcional.get());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}

	public void buscarVarios() {
		List<Integer> ids = new LinkedList<Integer>();

		ids.add(1);
		ids.add(3);
		ids.add(10);

		Iterable<Categorias> categorias = repoCategorias.findAllById(ids);

		for (Categorias cat : categorias) {
			System.out.println(cat);
		}
	}

	public void readAll() {

		Iterable<Categorias> categorias = repoCategorias.findAll();

		for (Categorias cat : categorias) {
			System.out.println(cat);
		}

	}

	public void update() {

		Optional<Categorias> opcional = repoCategorias.findById(1);

		if (opcional.isPresent()) {

			Categorias catTemp = opcional.get();
			catTemp.setNombre("Ing Software");
			catTemp.setDescripcion("Desarrollo de softwre");
			repoCategorias.save(catTemp);
			System.out.println(opcional.get());
		} else {
			System.out.println("Categoria no encontrada");
		}

	}

	public void delete() {

		int IdCategoria = 1;

		repoCategorias.deleteById(IdCategoria);

	}

	public void deleteAll() {

		repoCategorias.deleteAll();
		System.out.println("Se han eliminado todos los elementos");
	}

	public void count() {

		long count = repoCategorias.count();
		System.out.println("Existen un total de " + count);
	}

	public void existe() {

		boolean existe = repoCategorias.existsById(1);

		System.out.println("La categoria existe: " + existe);
	}

	/***
	 * Metodo que regresa una lista de categorias
	 */

	private List<Categorias> getListCategorias() {

		List<Categorias> lista = new LinkedList<Categorias>();

		// Categoria 1
		Categorias cat1 = new Categorias();
		cat1.setNombre("Programador IA");
		cat1.setDescripcion("Programador de inteligencia articial");

		// Categoria 2
		Categorias cat2 = new Categorias();
		cat2.setNombre("Programador IA");
		cat2.setDescripcion("Programador de inteligencia articial");

		lista.add(cat1);
		lista.add(cat2);

		return lista;
	}

	/***
	 * Metodos interfaz JpaRepository
	 */

	public void buscarTodas() {
		List<Categorias> listaCategorias = repoCategorias.findAll();

		for (Categorias c : listaCategorias) {
			System.out.println(c.getId() + ".  " + c.getNombre());
		}
	}

	public void borrarTodosenBloque() {

		repoCategorias.deleteAllInBatch();
	}

	public void buscarTodosOrdenados() {

		List<Categorias> listCategorias = repoCategorias.findAll(Sort.by("nombre"));
		for (Categorias c : listCategorias) {
			System.out.println(c.getId() + ".  " + c.getNombre());
		}

	}

	public void buscarTodosPagination() {

		Page<Categorias> page = repoCategorias.findAll(PageRequest.of(1, 5));

		for (Categorias c : page.getContent()) {
			System.out.println(c.getId() + ".  " + c.getNombre());

		}
	}

	public void buscarTodosPaginationOrdenados() {

		Page<Categorias> page = repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
		System.out.println("Total registros " + page.getTotalElements());
		System.out.println("Total paginas " + page.getTotalPages());

		for (Categorias c : page.getContent()) {
			System.out.println(c.getId() + ".  " + c.getNombre());

		}

	}

	/***
	 * METODOS PARA VACANTE
	 */

	public void guardarVacante() {

		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de Matematicas");
		vacante.setDescripcion("Escuela primaria solicita profesor para curso de Matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de Matematicas</h1>");
		Categorias cat = new Categorias();
		cat.setId(15);
		vacante.setCategoria(cat);
		repoVacantes.save(vacante);
	}

	public void buscarTodasVacantes() {

		List<Vacante> listvacante = repoVacantes.findAll();

		for (Vacante v : listvacante) {
			System.out.println(v.getId() + ".  " + v.getNombre() + " --" + v.getCategoria().getNombre());
		}
	}

	public void buscarVacantePorEstatus() {

		List<Vacante> lista = repoVacantes.findByEstatus("Aprobada");
		System.out.println("Total registros " + lista.size());
		for (Vacante v : lista) {
			System.out.println(v.getId() + ".  " + v.getNombre() + " --" + v.getCategoria().getNombre());
		}
	}

	public void buscarVacantePorEstatusDestacado() {

		List<Vacante> lista = repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");

		System.out.println("Total registros " + lista.size());
		for (Vacante v : lista) {
			System.out.println(
					v.getId() + ".  " + v.getNombre() + " --" + v.getCategoria().getNombre() + " :" + v.getDestacado());
		}

	}

	public void buscarVacanteSalario() {
		List<Vacante> lista = repoVacantes.findBySalarioBetweenOrderBySalarioDesc(0, 14000);

		System.out.println("Total registros " + lista.size());
		for (Vacante v : lista) {
			System.out.println(
					v.getId() + ".  " + v.getNombre() + " --" + v.getCategoria().getNombre() + " :" + v.getSalario());
		}

	}

	public void buscarVacantesVariosEstatus() {

		String[] estatus = new String[] { "Eliminada", "Creada" };
		
		List<Vacante> lista = repoVacantes.findByEstatusIn(estatus);
		
		System.out.println("Total registros " + lista.size());
		for (Vacante v : lista) {
			System.out.println(
					v.getId() + ".  " + v.getNombre() + " --" + v.getCategoria().getNombre() + " :" + v.getEstatus());
		}

	}

	/**
	 * Creaccion de perfiles
	 * 
	 * @return
	 */

	public void createProfiles() {

		repoPerfiles.saveAll(getPerfiles());

	}

	private List<Perfil> getPerfiles() {

		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil per1 = new Perfil();
		per1.setPerfil("SUPERVISOR");

		Perfil per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR");

		Perfil per3 = new Perfil();
		per3.setPerfil("USUARIO");

		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
		return lista;
	}

	/***
	 * Creacion de usuarios
	 */
	public void createUser() {

		Usuario user = new Usuario();
		user.setNombre("Gustavo Lopez");
		user.setEmail("guslopezcallejsa@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("gusLopez");
		user.setPassword("123456");
		user.setEstatus(1);

		Perfil per1 = new Perfil();
		per1.setId(2);

		Perfil per2 = new Perfil();
		per2.setId(3);

		user.agregar(per1);
		user.agregar(per2);

		repoUsuarios.save(user);

	}

	public void buscarUser() {
		Optional<Usuario> optional = repoUsuarios.findById(1);

		if (optional.isPresent()) {
			Usuario user = optional.get();
			System.out.println("usuario:" + user);
			System.out.println("Con perfiles de");

			for (Perfil p : user.getPerfiles()) {
				System.out.print(p.getPerfil() + ", ");
			}
		} else {
			System.out.println("No se encontro el usuario");
		}

	}
}

package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.*;
import br.com.alura.forum.form.EstadoForm;
import br.com.alura.forum.modelo.*;
import br.com.alura.forum.repository.EstadoRepository;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/states")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	// POST - /api/states
	@PostMapping
	public ResponseEntity<EstadoDTO> cadastrar(@RequestBody EstadoForm form, UriComponentsBuilder uriBuilder) {
		Estado estado = form.converter(estadoRepository);
		estadoRepository.save(estado);

		URI uri = uriBuilder.path("/api/states{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDTO(estado));
	}

	// GET - /api/states
	@GetMapping()
	public List<EstadoDTO> pesquisaRegiaoEFiltra(@RequestParam(required = false) Regiao regiao,
			@RequestParam(required = false) String filtro) {

		List<Estado> estados;

		if (regiao == null) {
			estados = estadoRepository.findAll();
		} else {
			estados = estadoRepository.findAllByRegiao(regiao);
		}

		if (filtro != null) {
			if (filtro.equals("populacao")) {
				estados.sort(Comparator.comparing(Estado::getPopulacao, Comparator.reverseOrder()));
			} else if (filtro.equals("area")) {
				estados.sort(Comparator.comparing(Estado::getArea, Comparator.reverseOrder()));
			}
		}

		return EstadoDTO.converter(estados);
	}

	// GET - /api/states/{id}
	@GetMapping("/{id}")
	public ResponseEntity<EstadoDTO> detalhar(@PathVariable Long id) {

		Optional<Estado> estado = estadoRepository.findById(id);
		if (estado.isPresent()) {
			return ResponseEntity.ok(new EstadoDTO(estado.get()));
		}
		return ResponseEntity.notFound().build();

	}

	// PUT - /api/states/{id}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadoDTO> atualiza(@PathVariable Long id, @RequestBody @Valid EstadoForm form) {

		Optional<Estado> optional = estadoRepository.findById(id);
		if (optional.isPresent()) {
			Estado estado = form.atualiza(id, estadoRepository);
			return ResponseEntity.ok(new EstadoDTO(estado));

		}
		return ResponseEntity.notFound().build();

	}

	// DELETE - /api/states/{id}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Estado> optional = estadoRepository.findById(id);
		if (optional.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();

	}

}

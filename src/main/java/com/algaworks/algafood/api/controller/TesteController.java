package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}

	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaUnicoPorNome(@RequestParam("nome") String nome) {
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/restaurante/por-taxa-frete")
	public List<Restaurante> restautantesPorTaxaFrete(
			BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurante/por-nome")
	public List<Restaurante> restautantesPorNomeId(
			String nome, Long cozinhaId) {
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}

}

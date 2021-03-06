package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
		

		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
//	public void excluir(Long restauranteId) {
//		try {
//			restauranteRepository.remover(restauranteId);
//		} catch (EmptyResultDataAccessException e) {
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
//		} catch (DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(
//					String.format("Resturante de codigo %d não pode ser removido pois esta em uso", restauranteId));
//		}
//	}
}


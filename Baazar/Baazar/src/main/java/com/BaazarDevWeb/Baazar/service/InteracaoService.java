package com.BaazarDevWeb.Baazar.service;

import com.BaazarDevWeb.Baazar.model.Interacao;
import com.BaazarDevWeb.Baazar.model.Produto;
import com.BaazarDevWeb.Baazar.repository.InteracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteracaoService {

    @Autowired
    InteracaoRepository interacaoRepository;

    public Interacao cadastrarInteracao(Interacao interacao) {
        return interacaoRepository.save(interacao);
    }
}

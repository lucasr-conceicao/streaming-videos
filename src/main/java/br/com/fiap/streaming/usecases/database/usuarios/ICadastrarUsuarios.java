package br.com.fiap.streaming.usecases.database.usuarios;

import reactor.core.publisher.Mono;

public interface ICadastrarUsuarios {

    Mono<UsuariosResponse> cadastrarUsuario(UsuariosRequest request);
}

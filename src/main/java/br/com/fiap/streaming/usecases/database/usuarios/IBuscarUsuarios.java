package br.com.fiap.streaming.usecases.database.usuarios;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IBuscarUsuarios {

    Mono<UsuariosResponse> buscarUsuario(UUID usuarioId);
}

package br.com.fiap.streaming.usecases.database.usuarios;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IDeletarUsuarios {

    Mono<Void> deletarUsuario(UUID usuarioId);
}
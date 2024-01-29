package br.com.fiap.streaming.usecases.database.videos;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IDeletarVideos {

    Mono<Void> deletarVideo(UUID videoId);
}

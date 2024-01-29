package br.com.fiap.streaming.usecases.database.videos;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IBuscarVideos {

    Mono<VideosResponse> buscarVideo(UUID videoId);
}

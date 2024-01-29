package br.com.fiap.streaming.usecases.database.videos;

import reactor.core.publisher.Mono;

public interface ICadastrarVideos {

    Mono<VideosResponse> cadastrarVideos(VideosRequest request);
}

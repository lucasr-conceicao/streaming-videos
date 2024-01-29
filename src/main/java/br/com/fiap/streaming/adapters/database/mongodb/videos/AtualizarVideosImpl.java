package br.com.fiap.streaming.adapters.database.mongodb.videos;


import br.com.fiap.streaming.adapters.database.domain.Video;
import br.com.fiap.streaming.adapters.database.repository.VideosRepository;
import br.com.fiap.streaming.usecases.database.videos.IAtualizarVideos;
import br.com.fiap.streaming.usecases.database.videos.VideosRequest;
import br.com.fiap.streaming.usecases.database.videos.VideosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarVideosImpl implements IAtualizarVideos {

    private final VideosRepository videosRepository;

    @Override
    public Mono<VideosResponse> atualizarVideos(UUID videoId, VideosRequest request) {

        Mono<Video> videoMono = videosRepository.findById(videoId.toString());

        return videoMono.flatMap((existingWebflux) -> {
            existingWebflux.setTitulo(request.getTitulo());
            existingWebflux.setUrl(request.getUrl());
            existingWebflux.setDescricao(request.getDescricao());
            existingWebflux.setFavoritar(request.getFavoritar());
            return videosRepository.save(existingWebflux);
        }).map((webflux -> converterResponse(webflux).block()));
    }

    private Mono<VideosResponse> converterResponse(Video response) {
        VideosResponse videosResponse = VideosResponse.builder()
                .videoId(response.getId())
                .titulo(response.getTitulo())
                .descricao(response.getDescricao())
                .url(response.getUrl())
                .favoritar(response.getFavoritar())
                .usuario(response.getUsuario())
                .dataPublicacao(response.getDataPublicacao())
                .build();

        return Mono.just(videosResponse);
    }
}

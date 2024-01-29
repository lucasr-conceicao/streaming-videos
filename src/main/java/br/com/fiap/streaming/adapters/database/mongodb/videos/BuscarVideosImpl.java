package br.com.fiap.streaming.adapters.database.mongodb.videos;

import br.com.fiap.streaming.adapters.database.domain.Video;
import br.com.fiap.streaming.adapters.database.repository.VideosRepository;
import br.com.fiap.streaming.usecases.database.videos.IBuscarVideos;
import br.com.fiap.streaming.usecases.database.videos.VideosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarVideosImpl implements IBuscarVideos {

    private final VideosRepository videosRepository;

    @Override
    public Mono<VideosResponse> buscarVideo(UUID videoId) {

        return videosRepository.findById(videoId.toString()).flatMap(this::converterResponse);
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
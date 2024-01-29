package br.com.fiap.streaming.adapters.database.mongodb.videos;

import br.com.fiap.streaming.adapters.database.domain.Video;
import br.com.fiap.streaming.adapters.database.repository.VideosRepository;
import br.com.fiap.streaming.usecases.database.videos.ICadastrarVideos;
import br.com.fiap.streaming.usecases.database.videos.VideosRequest;
import br.com.fiap.streaming.usecases.database.videos.VideosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class CadastrarVideosImpl implements ICadastrarVideos {

    private final VideosRepository videosRepository;

    @Override
    public Mono<VideosResponse> cadastrarVideos(VideosRequest request) {
        return videosRepository.save(Video.builder()
                        .titulo(request.getTitulo())
                        .descricao(request.getDescricao())
                        .url(request.getUrl())
                        .favoritar(request.getFavoritar())
                        .usuario(request.getUsuario())
                        .dataPublicacao(LocalDateTime.now(ZoneId.of("UTC")))
                        .build())
                .flatMap(this::converterResponse);
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
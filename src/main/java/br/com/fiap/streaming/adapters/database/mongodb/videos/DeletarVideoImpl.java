package br.com.fiap.streaming.adapters.database.mongodb.videos;

import br.com.fiap.streaming.adapters.database.repository.VideosRepository;
import br.com.fiap.streaming.usecases.database.videos.IDeletarVideos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarVideoImpl implements IDeletarVideos {

    private final VideosRepository videosRepository;

    @Override
    public Mono<Void> deletarVideo(UUID videoId) {

        return videosRepository.deleteById(videoId.toString())
                .then(Mono.empty());
    }
}

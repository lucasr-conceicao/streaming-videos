package br.com.fiap.streaming.adapters.rest.controller;

import br.com.fiap.streaming.adapters.rest.dto.videos.VideosDtoRequest;
import br.com.fiap.streaming.adapters.rest.dto.videos.VideosDtoResponse;
import br.com.fiap.streaming.usecases.database.videos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideoController {

    private final ICadastrarVideos cadastrarVideos;
    private final IBuscarVideos buscarVideos;
    private final IDeletarVideos deletarVideos;
    private final IAtualizarVideos atualizarVideos;

    @PostMapping("/cadastrar")
    public Mono<ResponseEntity<VideosDtoResponse>> cadastrar(@RequestBody VideosDtoRequest request) {
        return cadastrarVideos.cadastrarVideos(montarRequest(request))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response)));
    }

    @GetMapping("/{videoId}")
    public Mono<ResponseEntity<VideosDtoResponse>> findById(@PathVariable UUID videoId) {
        return buscarVideos.buscarVideo(videoId)
                .map(response -> ResponseEntity.ok(converterResponse(response)));
    }

    @DeleteMapping("/{videoId}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID videoId) {
        return deletarVideos.deletarVideo(videoId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }

    @PutMapping("/{videoId}")
    public Mono<ResponseEntity<VideosDtoResponse>> atualizar(@PathVariable UUID videoId,
                                                             @RequestBody VideosDtoRequest request) {
        return atualizarVideos.atualizarVideos(videoId, montarRequest(request))
                .map(response -> ResponseEntity.ok(converterResponse(response)));
    }

    @PutMapping("/favoritar/video/{videoId}")
    public Mono<ResponseEntity<VideosDtoResponse>> atualizarFavoritos(@PathVariable UUID videoId,
                                                                      @RequestBody VideosDtoRequest request) {
        return atualizarVideos.atualizarVideos(videoId, montarRequest(request))
                .map(response -> ResponseEntity.ok(converterResponse(response)));
    }

    private VideosRequest montarRequest(VideosDtoRequest request) {
        return VideosRequest.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .url(request.getUrl())
                .favoritar(request.getFavoritar())
                .usuario(request.getUsuario())
                .build();
    }

    private VideosDtoResponse converterResponse(VideosResponse response) {
        return VideosDtoResponse.builder()
                .videoId(response.getVideoId())
                .titulo(response.getTitulo())
                .descricao(response.getDescricao())
                .url(response.getUrl())
                .favoritar(response.getFavoritar())
                .usuario(response.getUsuario())
                .dataPublicacao(response.getDataPublicacao())
                .build();
    }
}

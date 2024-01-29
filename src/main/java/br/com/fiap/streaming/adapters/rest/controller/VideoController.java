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

//    @GetMapping
//    public Mono<PageImpl<VideoController>> findAll(@RequestParam(defaultValue = "0") int page,
//                                                   @RequestParam(defaultValue = "10") int size,
//                                                   @RequestParam(defaultValue = "DESC") String direction,) {
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "dataPublicacao");
//        return this.videoService.findAll(pageRequest, videoCriteria);
//    }
//
//    @GetMapping("/{id}")
//    public Mono<VideoController> findById(@PathVariable UUID id) {
//        return this.videoService.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> update(@PathVariable UUID id, @RequestBody VideoController video) {
//        return this.videoService.update(id, video);
//    }
//

//
//
//    /**
//     * --------------------------------------------------------------
//     * Ao salvar o favorito, incrementar o contador da entidade Video
//     * --------------------------------------------------------------
//     * Recuperar o vídeos da base (mongo -findById)
//     * Incrementar o contador de favoritos (vídeos)
//     * Adicionar na lista do usuário
//     * Persistir a entidade vídeo
//     * Persistir a entidade usuário (salvar somente o ID do vídeo)
//     * --------------------------------------------------------------
//     * Observação: Implentei a opção para desfavoritar um vídeo
//     * --------------------------------------------------------------
//     */
//    @PutMapping("/favoritar/{userId}/{videoId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> favoriteVideo(
//            @PathVariable(name = "UserId") String userId,
//            @PathVariable(name = "videoId") UUID videoId) {
//
//        return this.videoService.favoriteVideo(userId, videoId);
//    }
//
//    @PutMapping("/desfavoritar/{userId}/{videoId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> defavoriteVideo(
//            @PathVariable(name = "UserId") String userId,
//            @PathVariable(name = "videoId") UUID videoId) {
//
//        return this.videoService.defavoriteVideo(userId, videoId);
//    }
//
//
//
//    /**
//     * O Endpoint Estatísticas deve retornar:
//     * 1- A quantidade total de vídeos (Count - findAll)
//     * 2- A quantidade de vídeos favoritados
//     * 3- Média de visualizações (quantidade de visualizações total / quantidade de vídeos)
//     */
//    @GetMapping("/estatisticas")
//    public Mono<Estatistica> estatisticas() {
//        return this.videoService.estatisticas();
//    }
}

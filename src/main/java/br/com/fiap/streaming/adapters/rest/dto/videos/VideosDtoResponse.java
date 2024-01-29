package br.com.fiap.streaming.adapters.rest.dto.videos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideosDtoResponse {

    private UUID videoId;
    private String titulo;
    private String descricao;
    private String url;
    private String favoritar;
    private UUID usuario;
    private LocalDateTime dataPublicacao;
}

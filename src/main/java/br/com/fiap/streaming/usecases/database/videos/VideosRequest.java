package br.com.fiap.streaming.usecases.database.videos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideosRequest {

    private String titulo;
    private String descricao;
    private String url;
    private String favoritar;
    private UUID usuario;
    private String dataPublicacao;
}

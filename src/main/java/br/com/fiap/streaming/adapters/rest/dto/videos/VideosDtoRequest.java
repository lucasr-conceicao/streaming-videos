package br.com.fiap.streaming.adapters.rest.dto.videos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideosDtoRequest {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("url")
    private String url;

    @JsonProperty("favoritar")
    private String favoritar;

    @JsonProperty("usuario")
    private UUID usuario;
}

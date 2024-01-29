package br.com.fiap.streaming.adapters.database.domain;

import br.com.fiap.streaming.adapters.database.enums.Categoria;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Document(collection = "videos")
public class Video {

    @Id
    @Builder.Default
    @MongoId(FieldType.STRING)
    private UUID id = UUID.randomUUID();

    private String titulo;
    private String descricao;
    private String url;
    private String favoritar;
    private UUID usuario;
    private LocalDateTime dataPublicacao;
    private Categoria categorias;
}

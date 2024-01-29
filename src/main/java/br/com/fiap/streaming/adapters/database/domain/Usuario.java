package br.com.fiap.streaming.adapters.database.domain;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Usuario {

    @Id
    @Builder.Default
    @MongoId(FieldType.STRING)
    private UUID id = UUID.randomUUID();

    private String name;

    @Indexed(unique = true)
    private String email;

    private String telefone;

    private UUID usuario;

//    private List<Video> favorites;
}


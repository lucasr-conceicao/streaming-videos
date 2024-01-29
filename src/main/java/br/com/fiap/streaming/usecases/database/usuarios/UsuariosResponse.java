package br.com.fiap.streaming.usecases.database.usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosResponse {

    private String id;
    private String nome;
    private String email;
    private String telefone;
}

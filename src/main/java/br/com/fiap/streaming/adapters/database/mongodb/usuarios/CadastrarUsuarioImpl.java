package br.com.fiap.streaming.adapters.database.mongodb.usuarios;

import br.com.fiap.streaming.adapters.database.domain.Usuario;
import br.com.fiap.streaming.adapters.database.repository.UsuarioRepository;
import br.com.fiap.streaming.usecases.database.usuarios.ICadastrarUsuarios;
import br.com.fiap.streaming.usecases.database.usuarios.UsuariosRequest;
import br.com.fiap.streaming.usecases.database.usuarios.UsuariosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CadastrarUsuarioImpl implements ICadastrarUsuarios {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Mono<UsuariosResponse> cadastrarUsuario(UsuariosRequest request) {
        return usuarioRepository.save(Usuario.builder()
                        .name(request.getNome())
                        .email(request.getEmail())
                        .telefone(request.getTelefone())
                        .build())
                .flatMap(this::converterResponse);
    }

    private Mono<UsuariosResponse> converterResponse(Usuario response) {
        UsuariosResponse usuariosResponse = UsuariosResponse.builder()
                .id(response.getId().toString())
                .nome(response.getName())
                .email(response.getEmail())
                .telefone(response.getTelefone())
                .build();

        return Mono.just(usuariosResponse);
    }
}

package br.com.fiap.streaming.adapters.database.mongodb.usuarios;

import br.com.fiap.streaming.adapters.database.domain.Usuario;
import br.com.fiap.streaming.adapters.database.repository.UsuarioRepository;
import br.com.fiap.streaming.usecases.database.usuarios.IAtualizarUsuarios;
import br.com.fiap.streaming.usecases.database.usuarios.UsuariosRequest;
import br.com.fiap.streaming.usecases.database.usuarios.UsuariosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarUsuarioImpl implements IAtualizarUsuarios {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Mono<UsuariosResponse> atualizarUsuario(UUID usuarioId, UsuariosRequest request) {

        Mono<Usuario> videoMono = usuarioRepository.findById(usuarioId.toString());

        return videoMono.flatMap((existingWebflux) -> {
            existingWebflux.setName(request.getNome());
            existingWebflux.setEmail(request.getEmail());
            existingWebflux.setTelefone(request.getTelefone());
            return usuarioRepository.save(existingWebflux);
        }).map((webflux -> converterResponse(webflux).block()));
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

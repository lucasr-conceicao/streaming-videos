package br.com.fiap.streaming.adapters.database.mongodb.usuarios;

import br.com.fiap.streaming.adapters.database.domain.Usuario;
import br.com.fiap.streaming.adapters.database.domain.Video;
import br.com.fiap.streaming.adapters.database.repository.UsuarioRepository;
import br.com.fiap.streaming.usecases.database.usuarios.IBuscarUsuarios;
import br.com.fiap.streaming.usecases.database.usuarios.UsuariosResponse;
import br.com.fiap.streaming.usecases.database.videos.VideosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarUsuarioImpl implements IBuscarUsuarios {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Mono<UsuariosResponse> buscarUsuario(UUID usuarioId) {

        return usuarioRepository.findById(usuarioId.toString()).flatMap(this::converterResponse);
    }

    private Mono<UsuariosResponse> converterResponse(Usuario response) {
        UsuariosResponse usuarioResponse = UsuariosResponse.builder()
                .id(response.getId().toString())
                .nome(response.getName())
                .email(response.getEmail())
                .telefone(response.getTelefone())
                .build();

        return Mono.just(usuarioResponse);
    }
}

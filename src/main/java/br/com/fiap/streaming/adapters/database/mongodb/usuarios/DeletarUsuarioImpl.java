package br.com.fiap.streaming.adapters.database.mongodb.usuarios;

import br.com.fiap.streaming.adapters.database.repository.UsuarioRepository;
import br.com.fiap.streaming.usecases.database.usuarios.IDeletarUsuarios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarUsuarioImpl implements IDeletarUsuarios {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Mono<Void> deletarUsuario(UUID usuarioId) {

        return usuarioRepository.deleteById(usuarioId.toString())
                .then(Mono.empty());
    }
}

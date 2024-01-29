package br.com.fiap.streaming.adapters.rest.controller;

import br.com.fiap.streaming.adapters.rest.dto.usuarios.UsuarioDtoRequest;
import br.com.fiap.streaming.adapters.rest.dto.usuarios.UsuarioDtoResponse;
import br.com.fiap.streaming.usecases.database.usuarios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IAtualizarUsuarios atualizarUsuarios;
    private final IBuscarUsuarios buscarUsuarios;
    private final ICadastrarUsuarios cadastrarUsuarios;
    private final IDeletarUsuarios deletarUsuarios;

    @PostMapping("/cadastrar")
    public Mono<ResponseEntity<UsuarioDtoResponse>> cadastrar(@RequestBody UsuarioDtoRequest request) {
        return cadastrarUsuarios.cadastrarUsuario(montarRequest(request))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response)));
    }

    @GetMapping("/{usuarioId}")
    public Mono<ResponseEntity<UsuarioDtoResponse>> findById(@PathVariable UUID usuarioId) {
        return buscarUsuarios.buscarUsuario(usuarioId)
                .map(response -> ResponseEntity.ok(converterResponse(response)));
    }

    @DeleteMapping("/{usuarioId}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID usuarioId) {
        return deletarUsuarios.deletarUsuario(usuarioId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }

    @PutMapping("/{usuarioId}")
    public Mono<ResponseEntity<UsuarioDtoResponse>> atualizar(@PathVariable UUID usuarioId,
                                                              @RequestBody UsuarioDtoRequest request) {
        return atualizarUsuarios.atualizarUsuario(usuarioId, montarRequest(request))
                .map(response -> ResponseEntity.ok(converterResponse(response)));
    }

    private UsuariosRequest montarRequest(UsuarioDtoRequest request) {
        return UsuariosRequest.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();
    }

    private UsuarioDtoResponse converterResponse(UsuariosResponse response) {
        return UsuarioDtoResponse.builder()
                .id(response.getId())
                .nome(response.getNome())
                .email(response.getEmail())
                .telefone(response.getTelefone())
                .build();
    }
}

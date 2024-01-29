package br.com.fiap.streaming.adapters.database.repository;

import br.com.fiap.streaming.adapters.database.domain.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends ReactiveMongoRepository<Usuario, String> {

}

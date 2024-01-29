package br.com.fiap.streaming.adapters.database.repository;

import br.com.fiap.streaming.adapters.database.domain.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosRepository extends ReactiveMongoRepository<Video, String> {

}

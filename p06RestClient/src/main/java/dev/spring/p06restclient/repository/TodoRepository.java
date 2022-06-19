package dev.spring.p06restclient.repository;


import dev.spring.p06restclient.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Integer> {

}

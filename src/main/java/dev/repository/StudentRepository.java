package dev.repository;

import dev.domain.Student;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends  CrudRepository<Student, Integer> {
    public Long countById(Integer id);
}


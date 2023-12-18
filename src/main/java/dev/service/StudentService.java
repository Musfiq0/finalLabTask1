package dev.service;

import dev.domain.Student;
import dev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired  private StudentRepository studentRepository;

    public List<Student> listAll() throws SQLException {
        return(List<Student>) studentRepository.findAll();
    }

    public void save(Student student){
        studentRepository.save(student);
    }

    public Student getById(Integer id) throws SQLException, UserPrincipalNotFoundException {
        Optional<Student> result = studentRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserPrincipalNotFoundException("Could not find any students with ID"+id);
    }

    public void delete(Integer id) throws SQLException, UserPrincipalNotFoundException {
        Long count= studentRepository.countById(id);
        if(count==null|| count==0){
            throw new UserPrincipalNotFoundException("Could not find any students with ID"+id);
        }
        studentRepository.deleteById(id);
    }
}

package gb.demo.controllers;

import gb.demo.converters.StudentConverter;
import gb.demo.dto.StudentDto;
import gb.demo.dto.StudentDtoWithoutId;
import gb.demo.entities.Student;
import gb.demo.exceptions.ResourceNotFoundException;
import gb.demo.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentsRepository studentsRepository;
    private final StudentConverter studentConverter;

    //http://localhost:8883/students-app/api/v1/students/1
    @GetMapping("/students/{id}")
        public StudentDto getStudent(@PathVariable Integer id) {
        return studentConverter.entityToDto(studentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студент с id: " + id + " не найден")));
    }

    //http://localhost:8883/students-app/api/v1/students
    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentsRepository.findAll();
        List<StudentDto> studentsDto= students.stream().map(studentConverter::entityToDto).collect(Collectors.toList());
        return studentsDto;
    }

    //curl -X DELETE http://localhost:8883/students-app/api/v1/students/3
    @DeleteMapping("/students/{id}")
    public List<StudentDto> deleteStudent(@PathVariable int id) {
        studentsRepository.deleteById(id);
        return getAllStudents();
    }

    //curl -X POST -H "Content-Type: application/json" --data '{"name":"test", "age":32}' http://localhost:8883/students-app/api/v1/students
    @PostMapping("/students")
    public void createStudent(@RequestBody StudentDtoWithoutId sd) {
        studentsRepository.saveAndFlush(studentConverter.dtoToEntity(sd));
    }

}

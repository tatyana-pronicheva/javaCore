package gb.demo.converters;

import gb.demo.dto.StudentDto;
import gb.demo.dto.StudentDtoWithoutId;
import gb.demo.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public StudentDto entityToDto(Student s) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(s.getId());
        studentDto.setAge(s.getAge());
        studentDto.setName(s.getName());
        return studentDto;
    }

    public Student dtoToEntity(StudentDtoWithoutId sd) {
        Student student = new Student();
        student.setAge(sd.getAge());
        student.setName(sd.getName());
        return student;
    }
}

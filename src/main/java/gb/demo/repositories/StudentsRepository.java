package gb.demo.repositories;

import gb.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentsRepository extends JpaRepository<Student, Integer> {

/*    @Query("DELETE Student s WHERE s.id = :id")
    void delete(int id);*/

    void deleteById(Integer id);
}

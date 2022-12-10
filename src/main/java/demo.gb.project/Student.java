package demo.gb.project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private Integer mark;

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }
    public Student(Integer id, String name, Integer mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }
}

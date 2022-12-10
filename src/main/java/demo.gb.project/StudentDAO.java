package demo.gb.project;

import org.hibernate.Transaction;

import java.util.List;

import static demo.gb.project.SessionFactoryService.currentSession;

public class StudentDAO {
    public void save(Student student){
        currentSession.save(student);
    }
    public void update(Student student){
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(student);
        transaction.commit();

    }
    public void delete(Student student){
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(student);
        transaction.commit();
    }
    public Student findById(Integer id){
        return currentSession.get(Student.class, id);
    }
    public List<Student> findAll(){
        return currentSession.createQuery("from Student").list();
    }
}

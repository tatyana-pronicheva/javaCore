package demo.gb.project;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Main {

    public static void main(String[] args){
         StudentDAO studentDAO = new StudentDAO();
            SessionFactoryService.openCurrentSession();
/*            for (int i = 0; i<1000; i++){
                Student student = new Student("Student" + i, i % 6);
                System.out.println(student.getName());
                System.out.println(student.getMark());
                studentDAO.save(student);
                }*/
         studentDAO.update(new Student(1, "test",34));
         studentDAO.delete(studentDAO.findById(4));
        System.out.println(studentDAO.findAll().get(3).getName());
        SessionFactoryService.closeCurrentSession();
    }


}

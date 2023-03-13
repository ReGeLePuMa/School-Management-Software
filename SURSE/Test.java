import java.util.Comparator;
import java.util.Map;


public class Test 
{
    public static void main(String[] args) 
    {
        User belea=UserFactory.getUser(UserFactory.UserType.STUDENT, "Belea", "Spartanul");
        User ionel=UserFactory.getUser(UserFactory.UserType.STUDENT, "Ionel", "Ionescu");
        User denji=UserFactory.getUser(UserFactory.UserType.STUDENT, "Denji", "Asa");
        User mother=UserFactory.getUser(UserFactory.UserType.PARENT, "Mama", "Ionescu");
        User father=UserFactory.getUser(UserFactory.UserType.PARENT, "Tata", "Ionescu"); 
        User teacher=UserFactory.getUser(UserFactory.UserType.TEACHER, "Profesor", "Pacate");
        User assistant=UserFactory.getUser(UserFactory.UserType.ASSISTANT, "Sas", "Tufis");
        
        ((Student)ionel).setMother((Parent)mother);
        ((Student)ionel).setFather((Parent)father);
        ((Student)denji).setMother((Parent)mother);

        Course course = new FullCourse.FullCourseBuilder()
                        .setNume("SO")
                        .setTitular((Teacher) teacher)
                        .setAssistants()
                        .setGrupe()
                        .setNote()
                        .setStrategy(new ExamStrategy())
                        .setSnapshot()
                        .build();
        course.addGroup("1",(Assistant)assistant,new Comparator<Student>() 
        {
            @Override
            public int compare(Student o1, Student o2)
            {
                    if(o1.equals(o2))
                    {
                        return 0;
                    }
                    else if(o1.getLastName().compareTo(o2.getLastName())==0)
                    {
                        return o1.getFirstName().compareTo(o2.getFirstName());
                    }
                    else return o1.getLastName().compareTo(o2.getLastName());
            }
        });    
        course.addStudent("1",(Student)belea);
        course.addStudent("1",(Student)ionel);
        course.addGrade(new Grade(course.getNume(),(Student)belea, 4d, 5d));
        course.addGrade(new Grade(course.getNume(),(Student)ionel,3d,3d));     

        Course course2 = new PartialCourse.PartialCourseBuilder()
                        .setNume("POO")
                        .setTitular((Teacher)UserFactory.getUser(UserFactory.UserType.TEACHER, "Karen", "Yoru"))
                        .setAssistants()
                        .setGrupe()
                        .setNote()
                        .setStrategy(new TotalStrategy())
                        .setSnapshot()
                        .build(); 
        course2.addGroup("1",(Assistant)assistant,new Comparator<Student>() 
        {
            @Override
            public int compare(Student o1, Student o2)
            {
                    if(o1.equals(o2))
                    {
                        return 0;
                    }
                    else if(o1.getLastName().compareTo(o2.getLastName())==0)
                    {
                        return o1.getFirstName().compareTo(o2.getFirstName());
                    }
                    else return o1.getLastName().compareTo(o2.getLastName());
            }
        });    
        course2.addStudent("1", new Student("Caen", "Kaktus"));
        course2.addStudent("1", new Student("Beton","Armand"));
        course2.addStudent("1", (Student)ionel);
        course2.addGrade(new Grade(course2.getNume(), new Student("Caen", "Kaktus"), 4d, 5d));
        course2.addGrade(new Grade(course2.getNume(), new Student("Beton","Armand"),3d,1d));     
        course2.addGrade(new Grade(course2.getNume(), (Student)ionel,6d,2d));                               
        System.out.println("Curs 1: "+ course.getNume() + "\nTeacher: " + course.getTitular());
        System.out.println("Elevi:" + course.getAllStudents());
        System.out.println("Elevi absolventi:" + course.getGraduatedStudents());
        System.out.println("Best Student:" + course.getBestStudent());
        System.out.println("Notele elevilor:" + course.gettAllStudentGrades());
        System.out.println("\n");
        System.out.println("Curs 2: "+ course2.getNume()+"\nTeacher: " + course2.getTitular());
        System.out.println("Elevi:" + course2.getAllStudents());
        System.out.println("Elevi absolventi:" + course2.getGraduatedStudents());
        System.out.println("Best Student:" + course2.getBestStudent()+"\n");
        System.out.println("Notele elevilor:" + course2.gettAllStudentGrades());
        
        System.out.println("Creeam backup la note");
        course.makeBackup();
        course.addGrade(new Grade(course.getNume(), new Student("Ionel", "Mihail"), 4d, 5d));
        course.addGrade(new Grade(course.getNume(), new Student("Scandura", "Lemnaru"),3d,3d));
        course.addGrade(new Grade(course.getNume(), new Student("Mario", "Luigi"),1d,1d));
        for(Map.Entry<Student,Grade> intrare: course.gettAllStudentGrades().entrySet())
        {
            System.out.println(intrare.getKey() + " " + intrare.getValue().getTotal());
        }
        System.out.println("Restauram backup la note");
        course.undo();
        for(Map.Entry<Student,Grade> intrare: course.gettAllStudentGrades().entrySet())
        {
            System.out.println(intrare.getKey() + " " + intrare.getValue().getTotal());
        }
        System.out.println("\n");

        Catalog catalog=Catalog.getInstance();
        catalog.addCourse(course);
        catalog.addCourse(course2);
        catalog.addObserver((Parent)mother);
        catalog.addObserver((Parent)father);
        catalog.notifyObservers(new Grade("POO", (Student)ionel,2.5d,3d));
 
        IVisitor v=new ScoreVisitor();
 
        v.visit((Teacher)teacher);
        System.out.println(((ScoreVisitor)v).getNoteExamen());
        v.visit((Assistant)assistant);
        System.out.println(((ScoreVisitor)v).getNoteParcurs());
    }
}
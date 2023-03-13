import java.util.Comparator;

@SuppressWarnings("unused")
public class Main
{
    public static void main(String[] args)
    {
        User belea=UserFactory.getUser(UserFactory.UserType.STUDENT, "Belea", "Spartanul");
        User ionel=UserFactory.getUser(UserFactory.UserType.STUDENT, "Ionel", "Ionescu");
        User denji=UserFactory.getUser(UserFactory.UserType.STUDENT, "Denji", "Asa");
        User beton=UserFactory.getUser(UserFactory.UserType.STUDENT, "Beton", "Armand");
        User caen=UserFactory.getUser(UserFactory.UserType.STUDENT, "Caen", "Kaktus");
        User mother=UserFactory.getUser(UserFactory.UserType.PARENT, "Mama", "Ionescu");
        User father=UserFactory.getUser(UserFactory.UserType.PARENT, "Tata", "Ionescu"); 
        User teacher=UserFactory.getUser(UserFactory.UserType.TEACHER, "Profesor", "Pacate");
        User assistant=UserFactory.getUser(UserFactory.UserType.ASSISTANT, "Sas", "Tufis");
        
        ((Student)ionel).setMother((Parent)mother);
        ((Student)ionel).setFather((Parent)father);
        ((Student)denji).setMother((Parent)mother);

        Course course = new FullCourse.FullCourseBuilder()
                        .setNume("SO")
                        .setNr_Credite(5)
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
                        .setNr_Credite(5)
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
        course2.addStudent("1", (Student)ionel);
        course2.addStudent("1", (Student)caen);
        course2.addStudent("1", (Student)beton);
        course2.addGrade(new Grade(course2.getNume(), (Student)ionel, 4d, 5d));
        course2.addGrade(new Grade(course2.getNume(),(Student)caen,3d,1d));
        course2.addGrade(new Grade(course2.getNume(), (Student)beton,6d,2d));    
        Catalog catalog=Catalog.getInstance();
        catalog.addCourse(course);
        catalog.addCourse(course2);
        catalog.addObserver((Parent)mother);
        catalog.addObserver((Parent)father);  
        LoginPage login=new LoginPage();
    }
}
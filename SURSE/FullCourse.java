import java.util.ArrayList;

public class FullCourse extends Course
{
    public FullCourse(FullCourseBuilder builder)
    {
        super(builder);
    }
    public static class FullCourseBuilder extends CourseBuilder
    {
        @Override
        public Course build()
        {
            return new FullCourse(this);
        }
    }
    @Override
    public ArrayList<Student> getGraduatedStudents() 
    {
        ArrayList<Student> studenti_trecuti=new ArrayList<Student>();
        for(Grade materie: this.getNote())
        {
            if(materie.getPartialScore()>=3 && materie.getExamScore()>=2)
            {
                studenti_trecuti.add(materie.getStudent());
            }
        }
        return studenti_trecuti;
    }
}
import java.util.ArrayList;

public class PartialCourse extends Course
{
    public PartialCourse(PartialCourseBuilder builder) 
    {
        super(builder);
    }
    public static class PartialCourseBuilder extends CourseBuilder
    {
        @Override
        public Course build() 
        {
            return new PartialCourse(this);
        }
    }
    @Override
    public ArrayList<Student> getGraduatedStudents() 
    {
        ArrayList<Student> studenti_trecuti=new ArrayList<Student>();
        for(Grade materie: this.getNote())
        {
            if(materie.getTotal()>=5)
            {
                studenti_trecuti.add(materie.getStudent());
            }
        }
        return studenti_trecuti;
    }
}
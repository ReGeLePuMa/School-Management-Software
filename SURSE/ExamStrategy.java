import java.util.ArrayList;

public class ExamStrategy implements IStrategy
{

    @Override
    public Student getBestStudent(ArrayList<Grade> materii) 
    {
        Double max=0.0;
        Student best=null;
        for(Grade nota: materii)
        {
            if(nota.getExamScore()>max)
            {
                max=nota.getExamScore();
                best=nota.getStudent();
            }
        }
        return best;
    }
    
}
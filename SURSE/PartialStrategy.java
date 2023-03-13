import java.util.ArrayList;

public class PartialStrategy implements IStrategy
{
    @Override
    public Student getBestStudent(ArrayList<Grade> materii) 
    {
        Double max=0.0;
        Student best=null;
        for(Grade nota: materii)
        {
            if(nota.getPartialScore()>max)
            {
                max=nota.getPartialScore();
                best=nota.getStudent();
            }
        }
        return best;
    }
    
}
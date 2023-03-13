import java.util.ArrayList;

public class TotalStrategy implements IStrategy 
{
    @Override
    public Student getBestStudent(ArrayList<Grade> materii) 
    {
        Double max=0.0;
        Student best=null;
        for(Grade nota: materii)
        {
            if(nota.getTotal()>max)
            {
                max=nota.getTotal();
                best=nota.getStudent();
            }
        }
        return best;
    }
}
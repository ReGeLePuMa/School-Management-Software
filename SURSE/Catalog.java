import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
public class Catalog implements ISubject
{
    private static Catalog instanta=null;
    private ArrayList<Course> materii;
    private ArrayList<IObserver> parinti;
    private Map<IObserver,Vector<Notification>> notificari;
    private Catalog()
    {
        this.materii=new ArrayList<>();
        this.parinti=new ArrayList<>();
        this.notificari=new HashMap<>();
    }
    public static Catalog getInstance()
    {
        if(instanta==null)
        {
            instanta=new Catalog();
        }
        return instanta;
    }
    public ArrayList<Course> getMaterii()
    {
        return materii;
    }
    public ArrayList<IObserver> getParinti()
    {
        return parinti;
    }
    public Map<IObserver,Vector<Notification>> getNotificari()
    {
        return notificari;
    }
    public void addCourse(Course c)
    {
        materii.add(c);
    }
    public void removeCourse(Course c)
    {
        materii.remove(c);
    }
    @Override
    public void addObserver(IObserver observer) 
    {
        parinti.add(observer);
        notificari.put(observer,new Vector<>());
    }
    @Override
    public void removeObserver(IObserver observer) 
    {
        parinti.remove(observer);
        notificari.remove(observer);
    }
    @Override
    public void notifyObservers(Grade grade) 
    {
        for(IObserver parent : parinti)
        {
            if(grade.getStudent().getFather().equals(parent) || grade.getStudent().getMother().equals(parent))
            {
                Notification notificare=new Notification(" a luat nota ", (Parent)parent, grade);
                notificari.get(parent).add(notificare.cloneaza());
                parent.update(notificare);
            }
        }     
    }
}

import java.util.Collections;
import java.util.Comparator;

public class Group extends SortedList<Student>
{
    private Assistant asitent;
    private String ID;
    public Group()
    {
        super();
    }
    public Group(String ID, Assistant assistant)
    {
        this();
        this.ID=ID;
        this.asitent=assistant;
    }
    public Group(String ID, Assistant assistant, Comparator<Student> comp)
    {
        super(comp);
        this.asitent=assistant;
        this.ID=ID;
    }
    public String getID()
    {
        return this.ID;
    }
    public Assistant getAssistant()
    {
        return this.asitent;
    }
    public void setAssistant(Assistant assistant)
    {
        this.asitent=assistant;
    }
    public void setID(String ID)
    {
        this.ID=ID;
    }
    @Override
    public boolean add(Student student)
    {
        if(!this.contains(student))
        {
            super.add(student);
            Collections.sort(this,this.getComparator());
        }
        return false;
    }
}
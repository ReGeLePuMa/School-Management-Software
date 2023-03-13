import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortedList<T> extends ArrayList<T>
{
    private Comparator<T> comp;
    public SortedList()
    {
        super();
        this.comp=null;
    }
    public SortedList(Comparator<T> comp)
    {
        this();
        this.comp=comp;
    }
    public void setComparator(Comparator<T> comp)
    {
        this.comp=comp;
    }
    public Comparator<T> getComparator()
    {
        return this.comp;
    }
    @Override
    public boolean add(T e)
    {
        if(e instanceof Grade)
        {
            Grade grade=(Grade)e;
            for(int i=0;i<this.size();i++)
            {
                Grade g=(Grade)this.get(i);
                if(g.getStudent().equals(grade.getStudent()))
                {
                    ((Grade)this.get(i)).setExamScore(grade.getExamScore());
                    ((Grade)this.get(i)).setPartialScore(grade.getPartialScore());
                    return true;
                }
            }
        }
        if(super.add(e)==false)
        {
            return false;
        }
        Collections.sort(this,comp);
        return true;
    }
    @Override
    public void add(int index, T e)
    {
        super.add(index, e);
        Collections.sort(this,comp);
    }
    @Override
    public boolean remove(Object o)
    {
        if(super.remove(o)==false)
        {
            return false;
        }
        Collections.sort(this,comp);
        return true;
    }
    @Override
    public T remove(int index)
    {
        T elem=super.remove(index);
        Collections.sort(this,comp);
        return elem;
    }
}
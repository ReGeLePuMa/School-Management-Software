import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreVisitor implements IVisitor
{
    private Map<Teacher,ArrayList<Tuple<Student,String,Double>>> noteExamen;
    private Map<Assistant,ArrayList<Tuple<Student,String,Double>>> noteParcurs;
    public ScoreVisitor()
    {
        this.noteExamen = new HashMap<>();
        this.noteParcurs = new HashMap<>();
    }
    private static class Tuple<K,V,E>
    {
        private K e1;
        private V e2;
        private E e3;
        public Tuple(K e1, V e2, E e3)
        {
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
        }
        @Override
        public String toString()
        {
            return "{"+e1.toString()+" "+e2.toString()+" "+e3.toString()+"}";
        }
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o)
        {
            if(o instanceof Tuple)
            {
                Tuple<K,V,E> tuple=(Tuple<K,V,E>)o;
                return (this.e1.equals(tuple.e1) && this.e2.equals(tuple.e2) && this.e3.equals(tuple.e3));
            }
            return false;
        }
    }
    private boolean validate2(Map<Teacher,ArrayList<Tuple<Student,String,Double>>> note,Tuple<Student,String,Double> nota)
    {
        for(Map.Entry<Teacher,ArrayList<Tuple<Student,String,Double>>> intrare: note.entrySet())
        {
            for(Tuple<Student,String,Double> student: intrare.getValue())
            {
                if(student.equals(nota))
                {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean validate(Map<Assistant,ArrayList<Tuple<Student,String,Double>>> note,Tuple<Student,String,Double> nota)
    {
        for(Map.Entry<Assistant,ArrayList<Tuple<Student,String,Double>>> intrare: note.entrySet())
        {
            for(Tuple<Student,String,Double> student: intrare.getValue())
            {
                if(student.equals(nota))
                {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void visit(Assistant assistant) 
    {
        ArrayList<Tuple<Student,String,Double>> note = new ArrayList<>();
        for(Course materie: Catalog.getInstance().getMaterii())
        {
            for(Map.Entry<String, Group> intrare :materie.getGrupe().entrySet())
            {
                if(intrare.getValue().getAssistant().equals(assistant))
                {
                    for(Student student: intrare.getValue())
                    {
                        Tuple<Student,String,Double> nota=new Tuple<Student,String,Double>(student,materie.getNume(),materie.getGrade(student).getPartialScore());
                        if(!validate(noteParcurs,nota))
                        {
                            note.add(nota);
                            Catalog.getInstance().notifyObservers(materie.getGrade(student));
                        }
                    }
                }
            }
        }
        noteParcurs.put(assistant,note);
    }
    @Override
    public void visit(Teacher teacher) 
    {
        ArrayList<Tuple<Student,String,Double>> note = new ArrayList<>();
        for(Course materie: Catalog.getInstance().getMaterii())
        {
            if(materie.getTitular().equals(teacher))
            {
                for(Grade student:materie.getNote())
                {
                    Tuple<Student,String,Double> nota=new Tuple<Student,String,Double>(student.getStudent(),materie.getNume(),student.getExamScore());
                    if(!validate2(noteExamen,nota))
                    {
                        note.add(nota);
                        Catalog.getInstance().notifyObservers(student);
                    }
                }
            }
        }
        noteExamen.put(teacher,note);
    }
    public Map<Teacher,ArrayList<Tuple<Student,String,Double>>> getNoteExamen()
    {
        return this.noteExamen;
    }
    public Map<Assistant,ArrayList<Tuple<Student,String,Double>>> getNoteParcurs()
    {
        return this.noteParcurs;
    }
}
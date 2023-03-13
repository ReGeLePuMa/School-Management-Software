import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Course
{
    private String nume;
    private Teacher titular;
    private Set<Assistant> assistants;
    private SortedList<Grade> note;
    private Map<String,Group> grupe;
    private IStrategy strategie;
    private Snapshot backup;
    private int nr_Credite;
    public Course(CourseBuilder builder)
    {
        this.nume=builder.nume;
        this.nr_Credite=builder.nr_Credite;
        this.titular=builder.titular;
        this.assistants=builder.assistants;
        this.note=builder.note;
        this.grupe=builder.grupe;
        this.strategie=builder.strategie;
        this.backup=builder.backup;
    }
    public String getNume()
    {
        return this.nume;
    }
    public Teacher getTitular()
    {
        return this.titular;
    }
    public Set<Assistant> getAssistants()
    {
        return this.assistants;
    }
    public SortedList<Grade> getNote()
    {
        return this.note;
    }
    public Map<String,Group> getGrupe()
    {
        return this.grupe;
    }
    public int getNr_Credite()
    {
        return this.nr_Credite;
    }
    private static class Snapshot
    {
        private SortedList<Grade> notele;
        public Snapshot()
        {
            this.notele=new SortedList<Grade>();
        }
        public Snapshot(SortedList<Grade> notele) throws CloneNotSupportedException
        {
            this();
            for(Grade nota: notele)
            {
                this.notele.add((Grade)nota.clone());
            }
        }
        public SortedList<Grade> getNotele()
        {
            return this.notele;
        }
    }
    public void makeBackup()
    {
        try
        {
            this.backup=new Snapshot(this.note);
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
    } 
    public void undo()
    {
        if(!this.backup.getNotele().isEmpty())
        {
            this.note=this.backup.getNotele();
        }
    }
    public abstract static class CourseBuilder
    {
        private String nume;
        private Teacher titular;
        private Set<Assistant> assistants;
        private SortedList<Grade> note;
        private Map<String,Group> grupe;
        private IStrategy strategie;
        private Snapshot backup;
        private int nr_Credite;
        public CourseBuilder setNume(String nume)
        {
            this.nume=nume;
            return this;
        }
        public CourseBuilder setTitular(Teacher titular)
        {
            this.titular=titular;
            return this;
        }
        public CourseBuilder setNr_Credite(int nr_Credite)
        {
            this.nr_Credite=nr_Credite;
            return this;
        }
        public CourseBuilder setAssistants()
        {
            this.assistants=new HashSet<Assistant>();
            return this;
        }
        public CourseBuilder setNote()
        {
            this.note=new SortedList<Grade>();
            return this;
        }
        public CourseBuilder setGrupe()
        {
            this.grupe=new HashMap<String,Group>();
            return this;
        }
        public CourseBuilder setStrategy(IStrategy strategie)
        {
            this.strategie=strategie;
            return this;
        }
        public CourseBuilder setSnapshot()
        {
            this.backup=new Snapshot();
            return this;
        }
        public abstract Course build();
    }
    public void addAssistant(String ID,Assistant assistant)
    {
        grupe.get(ID).setAssistant(assistant);
        this.assistants.add(assistant);
    }
    public void addStudent(String ID, Student student)
    {
       grupe.get(ID).add(student);
    }
    public void addGroup(Group group)
    {
        this.grupe.put(group.getID(),group);
        this.addAssistant(group.getID(), group.getAssistant());
    }
    public void addGroup(String ID, Assistant assistant)
    {
        this.grupe.put(ID,new Group(ID,assistant));
        this.addAssistant(ID, assistant);
    }
    public void addGroup(String ID, Assistant assist, Comparator<Student> comp)
    {
        this.grupe.put(ID,new Group(ID,assist,comp));
        this.addAssistant(ID, assist);
    }
    public void addGrade(Grade grade)
    {
        this.note.add(grade);
    }
    public Grade getGrade(Student student)
    {
        for(Grade grade:this.note)
        {
            if(grade.getStudent().equals(student))
            {
                return grade;
            }
        }
        return null;
    }
    public ArrayList<Student> getAllStudents()
    {
        ArrayList<Student> studenti=new ArrayList<>();
        for(Group grupa:this.grupe.values())
        {
            for(Student student:grupa)
            {
                studenti.add(student);
            }
        }
        return studenti;
    }
    public HashMap<Student, Grade> gettAllStudentGrades()
    {
        HashMap<Student,Grade> studenti=new HashMap<>();
        for(Grade grade:this.note)
        {
            studenti.put(grade.getStudent(),grade);
        }
        return studenti;
    }
    public Student getBestStudent()
    {
        return this.strategie.getBestStudent(this.note);
    }
    public abstract ArrayList<Student> getGraduatedStudents();
}
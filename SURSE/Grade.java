public class Grade implements Comparable<Grade>, Cloneable
{
    private Double partialScore, examScore;
    private Student student;
    private String course;
    Grade()
    {
        super();
    }
    Grade(String course)
    {
        this.course=course;
    }
    Grade(String course, Student student)
    {
        this(course);
        this.student=student;
    }
    Grade(String course, Student student, Double partialScore, Double examScore)
    {
        this(course, student);
        this.partialScore=partialScore;
        this.examScore=examScore;
    }
    public void setCourse(String course)
    {
        this.course=course;
    }
    public void setStudent(Student student)
    {
        this.student=student;
    }
    public Double setPartialScore(Double partialScore)
    {
        this.partialScore=partialScore;
        return partialScore;
    }
    public Double setExamScore(Double examScore)
    {
        this.examScore=examScore;
        return examScore;
    }
    public String getCourse()
    {
        return this.course;
    }
    public Student getStudent()
    {
        return this.student;
    }
    public Double getPartialScore()
    {
        return this.partialScore;
    }
    public Double getExamScore()
    {
        return this.examScore;
    }
    public Double getTotal()
    {
        return this.partialScore+this.examScore;
    }
    @Override
    public String toString()
    {
        return "Partial:"+this.partialScore+",Exam:"+this.examScore+",Total:"+this.getTotal();
    }
    @Override
    public int compareTo(Grade o) 
    {
        return -this.getTotal().compareTo(o.getTotal());
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Grade clona=(Grade)super.clone();
        clona.course=new String(this.course);
        clona.student=new Student(this.getStudent().getFirstName(),this.getStudent().getLastName());
        clona.student.setFather(this.getStudent().getFather());
        clona.student.setMother(this.getStudent().getMother());
        clona.examScore=this.examScore.doubleValue();
        clona.partialScore=this.partialScore.doubleValue();
        return clona;
    }
}
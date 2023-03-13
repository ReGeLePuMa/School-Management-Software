public class Notification
{
    private String mesaj;
    private Parent parinte;
    private Grade student;
    public Notification(String mesaj, Parent parinte, Grade student)
    {
        this.mesaj=mesaj;
        this.parinte=parinte;
        this.student=student;
    }
    private Notification(Notification mesaj)
    {
        this.mesaj=new String(mesaj.getMesaj());
        this.parinte=new Parent(mesaj.getParinte().getFirstName(),mesaj.getParinte().getLastName());
        try
        {
            this.student=(Grade)mesaj.getStudent().clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
    }
    public String getMesaj()
    {
        return mesaj;
    }
    public Parent getParinte()
    {
        return parinte;
    }
    private Grade getStudent()
    {
        return student;
    }
    public Student getNumeStudent()
    {
        return student.getStudent();
    }
    @Override
    public String toString()
    {
        return "Mesaj nou de la scoala pentru "+ parinte+" : \""+getNumeStudent()+mesaj+student.getTotal()+" la "+student.getCourse()+"\"";
    }
    public Notification cloneaza()
    {
        return new Notification(this);
    }
}
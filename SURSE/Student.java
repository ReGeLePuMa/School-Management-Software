public class Student extends User
{
    private Parent mama,tata;
    Student(String firstName, String lastName)
    {
        super(firstName,lastName);
        this.mama=new Parent("", lastName);
        this.tata=new Parent("", lastName);
    }
    public void setMother(Parent mother)
    {
        this.mama=mother;
    }
    public void setFather(Parent father)
    {
        this.tata=father;
    }
    public Parent getMother()
    {
        return this.mama;
    }
    public Parent getFather()
    {
        return this.tata;
    }
    @Override
    public boolean equals(Object o)
    {
        return super.equals(o);
    }
}
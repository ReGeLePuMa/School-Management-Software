public class Teacher extends User implements IElement
{
    Teacher(String firstName, String lastName)
    {
        super(firstName,lastName);
    }

    @Override
    public void accept(IVisitor visitor) 
    {
        visitor.visit(this);
    }
    
}
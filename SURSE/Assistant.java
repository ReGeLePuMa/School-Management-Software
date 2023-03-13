public class Assistant extends User implements IElement
{
    Assistant(String firstName, String lastName)
    {
        super(firstName,lastName);
    }
    @Override 
    public boolean equals(Object o)
    {
       return super.equals(o);
    }
    @Override
    public void accept(IVisitor visitor) 
    {
        visitor.visit(this);
    }
}
public class Parent extends User implements IObserver
{
    Parent(String firstName, String lastName)
    {
        super(firstName,lastName);
    }
    @Override
    public void update(Notification notification) 
    {
        System.out.println(notification);   
    }
}
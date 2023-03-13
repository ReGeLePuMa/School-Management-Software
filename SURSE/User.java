public abstract class User 
{
    private String firstName, lastName;
    public User(String firstName, String lastName) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String toString() 
    {
        return firstName + " " + lastName;
    }
    public String getFirstName() 
    {
        return firstName;
    }
    public String getLastName() 
    {
        return lastName;
    }
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof User)
        {
            User user=(User)o;
            return (this.getFirstName().equals(user.getFirstName()) && this.getLastName().equals(user.getLastName()));
        }
        return false;
    }
}
    
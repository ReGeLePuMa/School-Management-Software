public class UserFactory
{
    public static enum UserType
    {
        STUDENT,
        PARENT,
        ASSISTANT,
        TEACHER
    }
    public static User getUser(UserType type, String firstName, String lastName)
    {
        switch(type)
        {
            case STUDENT:
            {
                return new Student(firstName,lastName);
            } 
            case PARENT:
            {
                return new Parent(firstName,lastName);
            }    
            case ASSISTANT:
            {
                return new Assistant(firstName,lastName);
            }    
            case TEACHER:
            {   
                return new Teacher(firstName,lastName);
            }   
            default: return null;
        }
    }
}
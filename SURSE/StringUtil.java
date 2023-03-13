import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtil
{
    public static String[] strtok(String sir, String delim)
    {
        Vector<String> tokeni=new Vector<>();
        StringTokenizer tokens=new StringTokenizer(sir, delim);
        while(tokens.hasMoreTokens())
        {
            tokeni.add(tokens.nextToken());
        }
        return tokeni.toArray(new String[tokeni.size()]);
    }
}
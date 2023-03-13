import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

@SuppressWarnings("unused")
public class LoginPage extends JFrame implements ActionListener 
{
    JLabel email,parola;
    JTextField mail;
    JPasswordField pass;
    JButton login,reset;
    JCheckBox showpass; 
    public LoginPage() 
    {
        super("Login Page");
        setLayout(null);
        setBounds(10,10,370,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        email=new JLabel("Email:");
        parola=new JLabel("Parola:");
        mail=new JTextField();
        pass=new JPasswordField();
        login=new JButton("Login");
        reset=new JButton("Reset");
        showpass=new JCheckBox("Arata parola");
        login.addActionListener(this);
        reset.addActionListener(this);
        showpass.addActionListener(this);
        email.setBounds(50,150,100,30);
        parola.setBounds(50,220,100,30);
        mail.setBounds(150,150,150,30);
        pass.setBounds(150,220,150,30);
        showpass.setBounds(150,250,150,30);
        login.setBounds(50,300,100,30);
        reset.setBounds(200,300,100,30);
        add(email);
        add(parola);
        add(mail);
        add(pass);
        add(showpass);
        add(login);
        add(reset);
        setVisible(true);
        setResizable(false);
    }
    public int checkPassword(String[] tokens,String[] tokens2)
    {
        int ok=0;
        for(Course c : Catalog.getInstance().getMaterii())
        {
            if(c.getAllStudents().contains(new Student(tokens[0],tokens[1])))
            {
                ok=1;
                break;
            }
            if(c.getTitular().equals(new Teacher(tokens[0],tokens[1])))
            {
                ok=2;
                break;
            }
            for(Assistant assistant:c.getAssistants())
            {
                if(assistant.equals(new Assistant(tokens[0],tokens[1])))
                {
                    ok=3;
                    break;
                }
            }
            for(IObserver parent : Catalog.getInstance().getParinti())
            {
                if(((Parent)parent).equals(new Parent(tokens[0], tokens[1])))
                {
                    ok=4;
                    break;
                }
            }
            if(ok!=0)
            {
                break;
            }
        }
        if(!tokens[0].equals(tokens2[0])||!tokens[1].equals(tokens2[1]))
        {
            ok=0;
        }
        return ok;
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource()==login) 
        {
            String utilizator=mail.getText();
            String parola=new String(pass.getPassword());
            String[] tokens=StringUtil.strtok(utilizator, ".@");
            String[] tokens2=StringUtil.strtok(parola, ".");
            if(utilizator.endsWith("@student.com"))
            {
                if(checkPassword(tokens, tokens2)!=1)
                {
                    JOptionPane.showMessageDialog(this,"Nu s-a putut face autentificarea");
                }
                else
                {
                    StudentPage pagina= new StudentPage(new Student(tokens[0],tokens[1]));
                }
            }
            else if(utilizator.endsWith("@teacher.com") || utilizator.endsWith("@assistant.com"))
            {
                Vector<Integer> ok = new Vector<>();
                ok.add(2);
                ok.add(3);
                if(!ok.contains(checkPassword(tokens, tokens2)))
                {
                    JOptionPane.showMessageDialog(this,"Nu s-a putut face autentificarea");
                }
                else
                {
                    IElement cadru_didactic;
                    if(utilizator.endsWith("@teacher.com") && checkPassword(tokens, tokens2)==2)
                    {
                        cadru_didactic=new Teacher(tokens[0],tokens[1]);
                    }
                    else cadru_didactic=new Assistant(tokens[0],tokens[1]);
                    TeacherPage pagina= new TeacherPage(cadru_didactic);
                }
            }
            else if(utilizator.endsWith("@parent.com"))
            {
                if(checkPassword(tokens, tokens2)!=4)
                {
                    JOptionPane.showMessageDialog(this,"Nu s-a putut face autentificarea");
                }
                else
                {
                  ParentPage pagina= new ParentPage(new Parent(tokens[0], tokens[1]));
                }
            }
            else JOptionPane.showMessageDialog(this,"Nu s-a putut face autentificarea");
        }
        if (e.getSource()==reset) 
        {
            mail.setText("");
            pass.setText("");
        }
        if (e.getSource()==showpass) 
        {
            if (showpass.isSelected()) 
            {
                pass.setEchoChar((char) 0);
            } 
            else pass.setEchoChar('*');
        }
    }
}
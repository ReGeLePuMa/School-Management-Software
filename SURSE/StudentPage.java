import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;

public class StudentPage extends JFrame
{
    Student student;
    JTable cursuri;
    JPanel curs;
    JTextArea linie;
    public StudentPage(Student student)
    {
        super(student.getFirstName()+" "+student.getLastName());
        this.student=student;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        Vector<Vector<Object>> tabel = new Vector<>();
        Vector<Object> nume= new Vector<>(100);
        nume.add("Curs");
        nume.add("Profesor");
        nume.add("Nota Partial");
        nume.add("Nota Examen");
        nume.add("Nota Totala");
        createTable(tabel);
        cursuri=new JTable(new DefaultTableModel(tabel,nume){
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }  
        });
        linie=new JTextArea();
        ListSelectionModel rowSM = cursuri.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                int index=rowSM.getMinSelectionIndex();
                String text="",text2="";
                Vector<Object> selected=tabel.get(index);
                for(int i=0;i<35;i++)
                {
                    text+="-";
                }
                text+="\nNume: "+selected.get(0)+"\n";
                text+="Profesor: "+selected.get(1)+"\n";
                text+="Asistenti: ";
                for(Course c: Catalog.getInstance().getMaterii())
                {
                    if(c.getNume().equals((String)selected.get(0)))
                    {
                        for(Assistant asistent: c.getAssistants())
                        {
                            text+=asistent+" ";
                        }
                        text+="\nNr. Credite: "+c.getNr_Credite()+"\n"; 
                        for(int i=0;i<35;i++)
                        {
                            text+="-";
                        }
                        for(Map.Entry<String, Group> intrare :c.getGrupe().entrySet())
                        {
                            if(intrare.getValue().contains(student))
                            {
                                text+="\nGrupa: "+intrare.getKey();
                                text+="\nAsistent: "+intrare.getValue().getAssistant();
                            }
                        }
                        text2+="\nAbsolvent: ";
                        if(c.getGraduatedStudents().contains(student))
                        {
                            text2+=" Da";
                        } else text2+=" Nu";
                    }
                }
                text+="\n";
                for(int i=0;i<35;i++)
                {
                    text+="-";
                }
                text+="\nNota Partial: "+selected.get(2)+"\n";
                text+="Nota Examen: "+selected.get(3)+"\n";
                text+="Nota Totala: "+selected.get(4);
                text+=(text2+"\n");
                for(int i=0;i<35;i++)
                {
                    text+="-";
                }
                linie.setText(text);

            }
        });
        curs=new JPanel(new GridLayout());
        curs.add(linie);
        add(curs,BorderLayout.LINE_END);
        add(new JScrollPane(cursuri),BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }
    void createTable(Vector<Vector<Object>> elemente)
    {
        for(Course c: Catalog.getInstance().getMaterii())
        {
            Vector<Object> linie=new Vector<>();
            if(c.getAllStudents().contains(this.student))
            {
                linie.add(c.getNume());
                linie.add(c.getTitular());
            }
            for(Grade s: c.getNote())
            {
                if(s.getStudent().equals(this.student))
                {
                    linie.add(s.getPartialScore());
                    linie.add(s.getExamScore());  
                    linie.add(s.getTotal());  
                }
            }
            if(linie.size()>0)
            {
                elemente.add(linie);
            }
        }
    }
}
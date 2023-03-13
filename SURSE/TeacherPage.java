import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import java.util.*;

public class TeacherPage extends JFrame implements ActionListener
{
    JTable table,grupe;
    JButton validate;
    JPanel curs;
    Teacher profesor=null;
    Assistant asistent=null;
    public TeacherPage(IElement cadru_didactic)
    {
        super();
        if(cadru_didactic instanceof Teacher)
        {
            this.profesor=(Teacher)cadru_didactic;
            setTitle(profesor.getFirstName()+" "+profesor.getLastName());
        }
        else if(cadru_didactic instanceof Assistant)
        {
            this.asistent=(Assistant)cadru_didactic;
            setTitle(asistent.getFirstName()+" "+asistent.getLastName());
        }
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        Vector<Vector<Object>> tabel=new Vector<>();
        Vector<Object> nume=new Vector<>(100);
        nume.add("Curs");
        nume.add("Nr. Credite");
        nume.add("Nr. Asistenti");
        nume.add("Nr. Studenti");
        nume.add("Nr. Studenti absolventi");
        nume.add("Cel mai bun student");
        nume.add("Rata de promovare");
        createTable(tabel);
        table=new JTable(new DefaultTableModel(tabel,nume){
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        });
        curs=new JPanel(new GridLayout(2,0));
        curs.setVisible(false);
        Vector<Object> nume2=new Vector<>(100);
        nume2.add("Student");
        nume2.add("Grupa");
        nume2.add("Nota Partial");
        nume2.add("Nota Examen");
        nume2.add("Nota Finala");
        nume2.add("Absolvent");
        grupe=new JTable(new DefaultTableModel(new Vector<Vector<Object>>(), nume2)
        {
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                if(profesor!=null)
                {
                    if(column==3)
                    {
                        return true;
                    }
                    else return false;
                }
                else if(asistent!=null)
                {
                    if(column==2)
                    {
                        return true;
                    }
                    else return false;
                }
                return false;
            }
        });
        grupe.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent me)
            {
                if(grupe.getModel().isCellEditable(grupe.getSelectedRow(), grupe.getSelectedColumn()))
                {
                    String value=JOptionPane.showInputDialog(null,"Introduceti nota:");
                    if(value!=null)
                    {
                        if(!value.isEmpty())
                        {
                            grupe.setValueAt(value, grupe.getSelectedRow(), grupe.getSelectedColumn());
                            updateCatalog();
                            String nume_curs=(String)table.getValueAt(table.getSelectionModel().getMinSelectionIndex(), 0);
                            for(Course c : Catalog.getInstance().getMaterii())
                            {
                                if(c.getNume().equals(nume_curs))
                                {
                                    String[] student=StringUtil.strtok((String)(grupe.getValueAt(grupe.getSelectedRow(), 0)), " ");
                                    Student elev=new Student(student[0],student[1]);
                                    grupe.setValueAt(c.getGrade(elev).getTotal(), grupe.getSelectedRow(), 4);
                                    if(c.getGraduatedStudents().contains(elev))
                                    {
                                        grupe.setValueAt("Da", grupe.getSelectedRow(), 5);
                                    } else grupe.setValueAt("Nu", grupe.getSelectedRow(), 5);
                                    table.setValueAt(c.getGraduatedStudents().size(), table.getSelectionModel().getMinSelectionIndex(), 4);
                                    table.setValueAt(c.getBestStudent().getFirstName()+" "+c.getBestStudent().getLastName(), table.getSelectionModel().getMinSelectionIndex(), 5);
                                    table.setValueAt(100.0*c.getGraduatedStudents().size()/c.getAllStudents().size()+"%", table.getSelectionModel().getMinSelectionIndex(), 6);
                                    break;
                                }
                            }
                        } 
                    }    
                }
                else JOptionPane.showMessageDialog(null,"Nu aveti dreptul sa modificati aceasta valoare!");  
            }
        });
        grupe.setVisible(false);
        validate=new JButton("Valideaza notele");
        validate.setSize(40, 30);
        validate.setVisible(false);
        DefaultTableModel model=(DefaultTableModel)grupe.getModel();
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                curs.setVisible(true);
                model.setRowCount(0);
                grupe.setVisible(true);
                validate.setVisible(true);
                Vector<Object> selected=tabel.get(rowSM.getMinSelectionIndex());
                int i=0;
                if(asistent==null)
                {
                    for(Course c: Catalog.getInstance().getMaterii())
                    {
                        if(c.getNume().equals((String)selected.get(0)))
                        {
                            for(Map.Entry<Student,Grade> intrare: c.gettAllStudentGrades().entrySet())
                            {
                                Vector<Object> linie=new Vector<>();
                                linie.add(intrare.getKey().getFirstName()+" "+intrare.getKey().getLastName());
                                for(Map.Entry<String, Group> entry :c.getGrupe().entrySet())
                                {
                                    if(entry.getValue().contains(intrare.getKey()))
                                    {
                                        linie.add(entry.getKey());
                                        break;
                                    }
                                }  
                                linie.add(intrare.getValue().getPartialScore());
                                linie.add(intrare.getValue().getExamScore());
                                linie.add(intrare.getValue().getTotal());
                                if(c.getGraduatedStudents().contains(intrare.getKey()))
                                {
                                    linie.add("Da");
                                }
                                else
                                {
                                    linie.add("Nu");
                                }
                                if(linie.size()>0)
                                {
                                    model.insertRow(i, linie);
                                    i++;
                                }
                            }
                        }
                    }
                }
                else
                {
                    for(Course c: Catalog.getInstance().getMaterii())
                    {
                        if(c.getNume().equals((String)selected.get(0)))
                        {
                            for(Map.Entry<String,Group> intrare : c.getGrupe().entrySet())
                            {
                                if(intrare.getValue().getAssistant().equals(asistent))
                                {
                                    for(Student s: intrare.getValue())
                                    {
                                        Vector<Object> linie=new Vector<>();
                                        linie.add(s.getFirstName()+" "+s.getLastName());
                                        linie.add(intrare.getKey());
                                        linie.add(c.gettAllStudentGrades().get(s).getPartialScore());
                                        linie.add(c.gettAllStudentGrades().get(s).getExamScore());
                                        linie.add(c.gettAllStudentGrades().get(s).getTotal());
                                        if(c.getGraduatedStudents().contains(s))
                                        {
                                            linie.add("Da");
                                        }
                                        else
                                        {
                                            linie.add("Nu");
                                        }
                                        if(linie.size()>0)
                                        {
                                            model.insertRow(i, linie);
                                            i++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } 
            }
        });
        curs.add(new JScrollPane(grupe));
        validate.addActionListener(this);
        curs.add(validate);
        add(curs,BorderLayout.LINE_END);
        add(new JScrollPane(table),BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }
    public void createTable(Vector<Vector<Object>> elemente)
    {
        if(this.asistent==null)
        {
            for(Course c: Catalog.getInstance().getMaterii())
            {
                Vector<Object> linie=new Vector<>();
                if(c.getTitular().equals(this.profesor))
                {
                    linie.add(c.getNume());
                    linie.add(c.getNr_Credite());
                    linie.add(c.getAssistants().size());
                    linie.add(c.getAllStudents().size());
                    linie.add(c.getGraduatedStudents().size());
                    linie.add(c.getBestStudent().getFirstName()+" "+c.getBestStudent().getLastName());
                    linie.add(100.0*c.getGraduatedStudents().size()/c.getAllStudents().size()+"%");
                }
                if(linie.size()>0)
                {
                    elemente.add(linie);
                }
            }
        }
        else
        {
            for(Course c: Catalog.getInstance().getMaterii())
            {
                Vector<Object> linie = new Vector<>();
                for(Assistant assistant: c.getAssistants())
                {
                    if(assistant.equals(this.asistent))
                    {
                        linie.add(c.getNume());
                        linie.add(c.getNr_Credite());
                        linie.add(c.getAssistants().size());
                        linie.add(c.getAllStudents().size());
                        linie.add(c.getGraduatedStudents().size());
                        linie.add(c.getBestStudent().getFirstName()+" "+c.getBestStudent().getLastName());
                        linie.add(100.0*c.getGraduatedStudents().size()/c.getAllStudents().size()+"%");
                    }
                }
                if(linie.size()>0)
                {
                    elemente.add(linie);
                }
            }
        }
    }
    public void updateCatalog()
    {
        String nume_curs=(String)this.table.getValueAt(this.table.getSelectionModel().getMinSelectionIndex(),0);
        Object[] linie=new Object[this.grupe.getColumnCount()];
        for(int i=0;i<this.grupe.getRowCount();i++)
        {
            for(int j=0;j<this.grupe.getColumnCount();j++)
            {
                linie[j]=this.grupe.getValueAt(i, j);
            }
            String[] student=StringUtil.strtok((String)linie[0], " ");
            for(Course c : Catalog.getInstance().getMaterii())
            {
                if(c.getNume().equals(nume_curs))
                {
                    Double partial,examen;
                    if(linie[2] instanceof Double)
                    {
                        partial=(Double)linie[2];
                    }
                    else partial=Double.parseDouble((String)linie[2]);
                    if(linie[3] instanceof Double)
                    {
                        examen=(Double)linie[3];
                    }
                    else examen=Double.parseDouble((String)linie[3]);
                    Student elev=new Student(student[0], student[1]);
                    for(Student elevi: c.getAllStudents())
                    {
                        if(elevi.equals(elev))
                        {
                            elev.setFather(elevi.getFather());
                            elev.setMother(elevi.getMother());
                        }
                    }
                    c.addGrade(new Grade(nume_curs,elev,partial,examen));
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() instanceof JButton)
        {
            updateCatalog();
            IVisitor visitor=new ScoreVisitor();
            if(this.profesor==null)
            {
                ((ScoreVisitor)visitor).visit(this.asistent);
            }
            else ((ScoreVisitor)visitor).visit(this.profesor);
        }
    }
}
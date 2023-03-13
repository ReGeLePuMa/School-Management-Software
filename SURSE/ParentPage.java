import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import javax.swing.event.*;


public class ParentPage extends JFrame
{
    Parent parinte;
    JTable notificari;
    JTextArea email;
    JPanel notificare;
    public ParentPage(Parent parinte)
    {
        super(parinte.getFirstName()+ " "+ parinte.getLastName());
        this.parinte=parinte;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        Vector<Object> nume=new Vector<>();
        nume.add("Index");
        nume.add("Expeditor");
        nume.add("Subiect");
        notificari=new JTable(new DefaultTableModel(this.getLista(),nume){
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }  
        });
        notificari.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        notificari.getColumnModel().getColumn(0).setMinWidth(50);
        notificari.getColumnModel().getColumn(0).setMaxWidth(50);
        notificari.getColumnModel().getColumn(1).setMinWidth(200);
        notificari.getColumnModel().getColumn(2).setMinWidth(200);
        notificari.getColumnModel().getColumn(2).setMaxWidth(Integer.MAX_VALUE);
        email=new JTextArea();
        email.setEditable(false);
        email.setLineWrap(true);
        email.setWrapStyleWord(true);
        notificare=new JPanel(new BorderLayout());
        notificare.setVisible(false);
        ListSelectionModel rowSM =(DefaultListSelectionModel) notificari.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                notificare.setVisible(true);
                int index=rowSM.getMinSelectionIndex();
                String text="";
                Vector<Object> selected=getLista().get(index);
                for(int i=0;i<65;i++)
                {
                    text+="-";
                }
                text+="\nExpeditor: "+selected.get(1)+"\n";
                text+="Destinatar: "+parinte+"\n";
                text+="Subiect: "+selected.get(2)+"\n";
                for(int i=0;i<65;i++)
                {
                    text+="-";
                }
                text+="\nContinut:\n"+Catalog.getInstance().getNotificari().get(getParinte()).get(index)+"\n";
                for(int i=0;i<65;i++)
                {
                    text+="-";
                }
                text+="\n";
                email.setText(text);
            }
        });
        notificare.setSize(300,300);
        notificare.add(email,BorderLayout.CENTER);
        add(notificare,BorderLayout.LINE_END);
        add(new JScrollPane(notificari),BorderLayout.CENTER);
        setVisible(true);
    }
    public Parent getParinte()
    {
        for(IObserver p: Catalog.getInstance().getParinti())
        {
            if(((Parent)p).equals(this.parinte))
            {
                return (Parent)p;
            }
        }
        return null;
    }
    public Vector<Vector<Object>> getLista()
    {
        Vector<Vector<Object>> lista=new Vector<>();
        for(int i=0;i<Catalog.getInstance().getNotificari().get(this.getParinte()).size();i++)
        {
            Vector<Object> intrare =new Vector<>();
            intrare.add(i+1);
            intrare.add("Universitatea Politehnica Bucuresti");
            intrare.add("Situatia scolara a elevului");
            lista.add(intrare);
        }
        return lista;
    }
}
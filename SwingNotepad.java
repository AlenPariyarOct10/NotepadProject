import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class NotePadWindow implements ActionListener {
    JFrame jf;
    TextArea ta;
    JMenuBar jmb;
    JMenu jm;
    JMenuItem [] menuItems;
    JFileChooser jch;
    JDialog dialog;

    public NotePadWindow()
    {
        jf = new JFrame("Alen Notepad");
        jf.setDefaultCloseOperation(3);
        jf.setSize(1080,720);

        jmb = new JMenuBar();
        jm = new JMenu("File");
        menuItems = new JMenuItem[]
                {
                        new JMenuItem("Open"),
                        new JMenuItem("Save")
                };
        jf.setJMenuBar(jmb);
        jmb.add(jm);
        jm.add(menuItems[0]);
        jm.add(menuItems[1]);
        ta = new TextArea(710,1070);
        jf.add(ta);
        jf.setVisible(true);
        menuItems[0].addActionListener(this);
        menuItems[1].addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e1)
    {
        if(e1.getActionCommand().toString().equals("Save"))
        {
            jch = new JFileChooser();
            jch.setSelectedFile(new File("untitled.txt"));
            jch.showSaveDialog(null);
            try{
                if(jch.getSelectedFile()!=null)
                {
                    String content = ta.getText();
                    System.out.println(jch.getSelectedFile().getAbsolutePath());
                    FileWriter fw = new FileWriter(jch.getSelectedFile().getAbsolutePath());
                    fw.write(content);
                    fw.close();

                }
            }catch (Exception ex)
            {
                ex.getMessage();
            }

        }else{
            jch = new JFileChooser();
            jch.showOpenDialog(null);
            try
            {
                if(jch.getSelectedFile() != null)
                {
                    FileReader fr = new FileReader(jch.getSelectedFile().getAbsolutePath());
                    StringBuffer txt = new StringBuffer("");
                    int tmp;
                    while((tmp=fr.read()) != -1)
                    {
                        txt.append((char)tmp);
                    }
                    ta.setText(String.valueOf(txt));
                }else{
                    JOptionPane.showMessageDialog(null,"Unable to open file");
                }
            }catch (Exception ex)
            {

            }


        }
    }

}
public class SwingNotepad {
    public static void main(String [] args)
    {
        new NotePadWindow();
    }
}

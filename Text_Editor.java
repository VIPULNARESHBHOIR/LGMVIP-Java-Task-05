import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.awt.GridLayout;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;



class Text_Editor implements ActionListener{

    JFrame frame;
    JMenuBar menu;
    JMenu File,Edit,Options,Theme;
    JTextArea texts;
    JScrollPane scrl;
    JMenuItem Dark,Normal,Save,Open,Close,Cut,Paste,Copy,New,Selectall,FontSize,FontStyle,Textcolor,Background;
    JPanel WinOptions;
    JTextField filename,dirname;
    JLabel Filelabel,Dirlabel;
    int newfont1=12;

    Text_Editor(){
        frame=new JFrame("VB_texteditor");
        Image i=Toolkit.getDefaultToolkit().getImage("null");
        frame.setIconImage(i);
        menu=new JMenuBar();

        //Menubaritems--------------------------------------
        File=new JMenu("File", false);
        Edit=new JMenu("Edit", false);
        Theme=new JMenu("Theme", false);
        Options=new JMenu("Options", false);
        menu.add(File);
        menu.add(Edit);
        menu.add(Options);
        menu.add(Theme);
        frame.setJMenuBar(menu);

        New=new JMenuItem("New", 0);
        Open=new JMenuItem("Open", 0);
        Save=new JMenuItem("Save" ,0);
        Close=new JMenuItem("Quit",0);

        Cut=new JMenuItem("Cut", 0);
        Paste=new JMenuItem("Paste", 0);
        Copy=new JMenuItem("Copy", 0);
        FontStyle=new JMenuItem("Fontstyle", 0);
        FontSize=new JMenuItem("Fontsize", 0);

        Textcolor=new JMenuItem("TextColor",0);
        Background=new JMenuItem("Background",0);

        Dark=new JMenuItem("DarkMode", 0);
        Normal=new JMenuItem("NormalMode", 0);

       //addding submenu to the file menu
        File.add(Open);
        File.add(New);
        File.add(Save);
        File.add(Close);
        
        //addding submenu to the Edit menu
        Edit.add(Copy);
        Edit.add(Cut);
        Edit.add(Paste);
        Edit.add(FontSize);
        Edit.add(FontStyle);

         //addding submenu to the themes menu
        Theme.add(Dark);
        Theme.add(Normal);

        //addding submenu to the Options menu
        Options.add(Textcolor);
        Options.add(Background);

        //creating area for text and other features--------------------------
        texts=new JTextArea(45,  60);
        frame.add(texts);

        scrl=new JScrollPane(texts);
        //scrl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scrl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrl);

        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        Close.addActionListener(this);
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        FontSize.addActionListener(this);
        FontStyle.addActionListener(this);
        Dark.addActionListener(this);
        Normal.addActionListener(this);
        Textcolor.addActionListener(this);
        Background.addActionListener(this);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent){}

            @Override
            public void windowClosing(WindowEvent e){
                int confirmExit = JOptionPane.showConfirmDialog(frame,"Do you want to closed?","Confirm to save!",JOptionPane.YES_NO_OPTION);
                if (confirmExit==JOptionPane.YES_OPTION){
                    frame.dispose();
                }
                else if (confirmExit==JOptionPane.NO_OPTION){
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
            @Override 
            public void windowDeiconified(WindowEvent windowEvent){}
            @Override 
            public void windowActivated(WindowEvent windowEvent){}
            @Override 
            public void windowIconified(WindowEvent windowEvent){}
            @Override 
            public void windowDeactivated(WindowEvent windowEvent){}
            @Override
            public void windowClosed(WindowEvent windowEvent){}
           

        });

        //------------------frame setting and adjustment-------------------

        frame.setSize(700, 800);
        frame.setResizable(false);
        frame.setLocation(300, 0);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    }

    //Editing--------------------------------

    @Override
    public void  actionPerformed(ActionEvent e){
        if (e.getSource()==Cut){
            texts.cut();
        }
        if (e.getSource()==Paste){
            texts.paste();
        }
        if (e.getSource()==Copy){
            texts.copy();
        }


        if (e.getSource()==FontSize){
            String fontsize=JOptionPane.showInputDialog(frame, "Font Size", JOptionPane.OK_CANCEL_OPTION);
            if (fontsize!=null){
                newfont1=Integer.parseInt(fontsize);
                Font font1=new Font(Font.SANS_SERIF, Font.PLAIN, newfont1);
                texts.setFont(font1);
            }

        }
        if (e.getSource()==FontStyle){
            Object[] options = { "sanse-serif", "serif","m o n o s p a c e" };
            Integer opt=JOptionPane.showOptionDialog(frame, "Choose a option","Fontstyles",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
            if (opt==0){
                Font font2=new Font(Font.SANS_SERIF, Font.PLAIN, newfont1);
                texts.setFont(font2);
            }
            if (opt==1){
                Font font2=new Font(Font.SERIF, Font.PLAIN, newfont1);
                texts.setFont(font2);
            }
            if (opt==2){
                Font font2=new Font(Font.MONOSPACED, Font.PLAIN, newfont1);
                texts.setFont(font2);
            }

        }

        //For options menu-------------------------------------------------------------------------

        if (e.getSource()==Background){
            Object[] options = { "DarkBlue","LightBlue","Black" };
            Integer opt=JOptionPane.showOptionDialog(frame, "Choose a option","Background colors",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
            if (opt==0){
                texts.setBackground(new Color(0, 255, 230));
            }
            if (opt==1){
                texts.setBackground(new Color(51, 173, 255));
            }
            if (opt==2){
                texts.setBackground(Color.BLACK);
            }
        }

        if (e.getSource()==Textcolor){
            Object[] options = { "Green","Aqua","Yellow"};
            Integer opt=JOptionPane.showOptionDialog(frame,  "Choose a option","Textcolors",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
            if (opt==0){
                texts.setForeground(new Color(102,255,51));
            }
            if (opt==1){
                texts.setForeground(new Color(0, 255, 255));
            }
            if (opt==2){
                texts.setForeground(Color.YELLOW);
            }
        }


        //file opening-------------------
        if(e.getSource()==Open){
            JFileChooser file=new JFileChooser();
            int k = file.showOpenDialog(frame);
            if (k == JFileChooser.APPROVE_OPTION){
                File file1=file.getSelectedFile();
                String pathoffile=file1.getPath();
                String nameoffile=file1.getName();
                frame.setTitle(nameoffile);

                try{
                    BufferedReader content=new BufferedReader(new FileReader(pathoffile));
                    String string1="";
                    String string2="";

                    while((string1=content.readLine())!=null)
                        string2+=string1+"\n";

                    texts.setText(string2);
                    content.close();
                }

                catch(Exception exp){
                    exp.printStackTrace();
                }

            }

        }

        // saving system //--------------------------------------
        if(e.getSource()==Save) {
            file_save();
        }
        if (e.getSource()==Close){
            System.exit(1);
        }
        if (e.getSource()==New){
            texts.setText("");
        }

        // Themes setting-------------------------------------------------
        if(e.getSource()==Dark){
            texts.setBackground(new Color(0,19,26));
            texts.setForeground(Color.WHITE);
        }
        if(e.getSource()==Normal){
            texts.setBackground(Color.WHITE);
            texts.setForeground(Color.BLACK);
            
        }

     

    }

    //method file_save() ----------
    public void file_save(){
        WinOptions=new JPanel(new GridLayout(2, 01));
        Filelabel=new JLabel("Name:");
        Dirlabel=new JLabel("Path:");
        filename=new JTextField();
        dirname=new JTextField();
        WinOptions.add(Filelabel);
        WinOptions.add(Dirlabel);
        WinOptions.add(filename);
        WinOptions.add(dirname);
        JOptionPane.showMessageDialog(frame, WinOptions);
        String content_of_file=texts.getText();
        String path_of_file=dirname.getText();
        String name_of_file=filename.getText();

        try{
            BufferedWriter content2=new BufferedWriter(new BufferedWriter(new FileWriter(path_of_file+"\\"+name_of_file)));
            content2.write(content_of_file);
            content2.close();
            JOptionPane.showMessageDialog(frame,"file saved SUCCESSFULLY");

        }catch(Exception exp2){
            JOptionPane.showMessageDialog(frame,"Give Proper path");

        }
    }




    public static void main(String[] args) {
        new Text_Editor();
    }
     

}




/*
Instruction: Click the File->open button, choose a java file, if there is no error in this file, you should add some error, then, click File->save for 
saving this file. Then, click Build->compile, you could get the error message on the textarea below, and I output the parse-error-message below too.
Besides, you should randomly put the cursor on the upper textarea, and press the F4, you could see the cursor will emerge on the top position of the error line,
then, you press F4 again, the cursor will jump to the next error line. The cursor will return to first error line, when cursor at the last error line and you
press F4 again.
*/
package texteditor;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class TextEditor extends JFrame {
    private Container c;
    private StringBuilder str;
    private JFileChooser chooser;
    private JTextArea area;
    private JTextField text;
    private JTextArea text1;
    private String javac_file_name;
    private String java_file_name1;
    private int error_position;
    private ArrayList<Integer> a;
    private int count = 0;
    
    public TextEditor(){
        super("TextEditor");
        c = getContentPane();
        setSize(800,800);
        c.setBackground(Color.WHITE);
        text = new JTextField(" ");
        c.add(text,BorderLayout.NORTH);
        area = new JTextArea(" ");
        Font f = new Font("Times",Font.BOLD,30);
        Font k = new Font("Times",Font.PLAIN,20); 
        area.setFont(k);
        JMenu m = new JMenu("File");
        JMenu mm = new JMenu("Build");
        JMenu mmm = new JMenu("Help");
        m.setFont(f);
        mm.setFont(f);
        mmm.setFont(f);
        JMenuItem mm1 = new JMenuItem("Compile");
        JMenuItem mm2 = new JMenuItem("Run");
        mm1.setFont(k);
        mm2.setFont(k);
        mm.add(mm1);
        mm.add(mm2);
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Save");
        JMenuItem m3 = new JMenuItem("Open");
        JMenuItem m4 = new JMenuItem("Quit"); 
        m1.setFont(k);
        m2.setFont(k);
        m3.setFont(k);
        m4.setFont(k);
        m.add(m1);
        m.add(m2);
        m.add(m3);
        m.add(m4);
        JMenuBar menubar = new JMenuBar();
        menubar.add(m);
        menubar.add(mm);
        menubar.add(mmm);
        setJMenuBar(menubar); 
        text1 = new JTextArea("Results: ");
        JScrollPane js = new JScrollPane(area);
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        js.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        c.add(js);
        c.add(text1,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        m4.addActionListener(new MyListener1());
        mm1.addActionListener(new MyListener4());
        m2.addActionListener(new MyListener3());
        mm2.addActionListener(new MyListener5());
        chooser = new JFileChooser();
        m3.addActionListener(new MyListener2());
        area.addKeyListener(new MyListener6());
        
    }
class MyJDialog extends JDialog{
    public MyJDialog(TextEditor frame){
        super(frame,"Ensure again",true);
        Container c = getContentPane();
        JLabel l = new JLabel("Do you want to save this?");
        JPanel p = new JPanel();
        JButton a = new JButton("Yes");
        JButton b = new JButton("NO");
        Font k = new Font("Times",Font.PLAIN,20); 
        l.setFont(k);
        c.add(l,BorderLayout.NORTH);
        c.add(p,BorderLayout.CENTER);
        p.add(a);
        p.add(b);
        setBounds(120,120,400,150);
        a.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}
    public void ShowFileContent(){
        try {
            BufferedReader buf = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
            //System.out.println(chooser.getSelectedFile().getAbsolutePath());
            str = new StringBuilder(" ");
            String singleline;
            while((singleline=buf.readLine())!=null){
                str.append(singleline+"\n");  
            }
        area.setText(str.toString());
        } catch (FileNotFoundException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SaveFileContent() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(chooser.getSelectedFile().getAbsolutePath()));
        String[] strs = area.getText().split("\n");
        for(String str : strs){
            bw.write(str);
            bw.newLine();
        }
        bw.close();
        System.out.println("save mission complete");
    }
    public void Compile() throws IOException{
            Process process = Runtime.getRuntime().exec("javac "+chooser.getSelectedFile().getAbsolutePath());
            BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName("GBK")));
            String single = null;
            StringBuilder str1 = new StringBuilder(" ");
            while((single = br1.readLine())!=null){
                str1.append(single);
                str1.append("\n");
            }
            String s = str1.toString();
            String[] str = s.split("[\\:\\.\\\\\\;\\,]+");
            StringBuilder str2 = new StringBuilder(" ");
            for(String i : str){
                str2.append(i);
                str2.append(" ");
            }
            text1.setText(str1.toString()+"\nAfter Parsing:\n"+str2.toString());
            String regex = "(([a-zA-Z])\\w+.java:[0-9]+)";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(s);
            ArrayList<String> list = new ArrayList<>();
            while(matcher.find()){
                list.add(matcher.group());
            }
            String str3 = " ";
            a = new ArrayList<>();
            for(String s1:list){
                str3 = s1.substring(s1.indexOf(":")+1,s1.length());
                System.out.println(s1);
                a.add(Integer.parseInt(str3));
            }
    }
    public void Show_Error_Position() throws BadLocationException{
        error_position = area.getLineStartOffset(a.get(count));
        area.setCaretPosition(error_position);
    }
    public void Run() throws IOException{
        File file = new File(chooser.getSelectedFile().getAbsolutePath());
        File upper_file = new File(file.getParent());
        String s = file.getParent();
        String upper_file_name = upper_file.getName();
        int a1 = upper_file_name.length();
        int a2 = s.length();
        String without_package = s.substring(0,a2-a1-1);
        javac_file_name = file.getName();
        java_file_name1 = javac_file_name.substring(0, javac_file_name.indexOf("."));
        String s1 = "java -classpath "+without_package+" "+upper_file_name+"."+java_file_name1;
        Process process = Runtime.getRuntime().exec(s1);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = null;
        while((line = br1.readLine())!=null){
            System.out.println(line);
        }
        StringBuilder str = new StringBuilder(" ");
        String singleline = null;
        while((singleline = br.readLine())!=null){
            str.append(singleline);
            str.append("\n");
        }
        text1.setText(str.toString());
        System.out.println(str.toString());
    }
    
class MyListener1 implements ActionListener{
    public void actionPerformed(ActionEvent e){
        new MyJDialog(TextEditor.this).setVisible(true);
    }
} 
class MyListener2 implements ActionListener{
    public void actionPerformed(ActionEvent e){
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(TextEditor.this);
        text.setText(chooser.getSelectedFile().getAbsolutePath());
        ShowFileContent();   
    }
}
class MyListener3 implements ActionListener{
    public void actionPerformed(ActionEvent e){
        try {
            SaveFileContent();
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
class MyListener4 implements ActionListener{
    public void actionPerformed(ActionEvent e){
        try {
            Compile();
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
class MyListener5 implements ActionListener{
    public void actionPerformed(ActionEvent e){
        try {
            Run();
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
class MyListener6 extends KeyAdapter{
    public void keyPressed(KeyEvent e){
        try {
            if(e.getKeyCode()==KeyEvent.VK_F4){
                Show_Error_Position();
                count++;
                if(count>=a.size()){
                    count=0;
                }    
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        }
    public static void main(String[] args) throws ParseException {
       new TextEditor();    
       new getFileTimestamp();
    }  
}

class getFileTimestamp{
    public getFileTimestamp() throws ParseException{
        File a = new File("C:\\Users\\pro\\Documents\\NetBeansProjects\\textEditor\\src\\texteditor\\TextEditor.java");
        File b = new File("C:\\Users\\pro\\Documents\\NetBeansProjects\\textEditor\\build\\classes\\texteditor\\TextEditor.class");
        System.out.println(".java file before format:"+a.lastModified()+"\n"+".class file before format:"+b.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println(".java file after format:"+sdf.format(a.lastModified())+"\n"+".class file after format:"+sdf.format(b.lastModified()));
        Date javadate = sdf.parse(sdf.format(a.lastModified()));
        Date classdate = sdf.parse(sdf.format(b.lastModified()));
        if(javadate.after(classdate)){
           // new MyJDialog(TextEditor.this).setVisible(true);
            System.out.println("no compile");
        }else
            System.out.println("compile completed");
        }
}


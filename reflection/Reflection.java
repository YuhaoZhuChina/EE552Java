
/* when you run this code, a window with two textfields will emerge, then you input the firstname, and press the Enter, the firstname that you input will be showed on the command window.
Refers to the lastname, repeat the steps above again.
*/


package reflection;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Reflection extends JFrame {
    private Container c;
    private JPanel p;
    private JTextField t1;
    private JTextField t2;
    private String className = "reflection.bean";
    public Reflection() throws Exception{
        super("Input Window");
        c = getContentPane();
        setSize(1000,400);
        t1 = new JTextField("FirstName:");
        t2 = new JTextField("LastName:");
        Font f = new Font("Times",Font.BOLD, 30);
        t1.setFont(f);
        t2.setFont(f);
        p = new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(t1);
        p.add(t2);
        c.add(p);
        Class c = Class.forName(className);             
        Constructor constructor = c.getConstructor(); 
        //Object obj = constructor.newInstance();
        buildGetterSetter(c);
        t1.addKeyListener(new KeyAdapter(){ 
            public void keyPressed(KeyEvent e){
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    try {
                        //System.out.println(t1.getText().substring(10));
                        Class c = Class.forName(className);
                        //System.out.println("your class if named: " + c.getName());
                        Constructor constructor = c.getConstructor();
                        Object obj = constructor.newInstance();
                        Method m1 = c.getMethod("setFirstName", java.lang.String.class);
                        m1.invoke(obj, t1.getText().substring(10));
                        Method m2 = c.getMethod("getFirstName");
                        System.out.println(m2.invoke(obj));
                        System.out.println();
                        System.out.println(obj.toString());
                        
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
        });
        t2.addKeyListener(new KeyAdapter(){ 
            public void keyPressed(KeyEvent e){
               if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    try {
                        //System.out.println(t2.getText().substring(9));
                        Class c = Class.forName(className);
                        //System.out.println("your class if named: " + c.getName());
                        Constructor constructor = c.getConstructor();
                        Object obj = constructor.newInstance();
                        Method m1 = c.getMethod("setLastName", java.lang.String.class);
                        m1.invoke(obj, t2.getText().substring(9));
                        System.out.println(obj.toString());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
        });
        setVisible(true); 
    }
       
    public static void buildGetterSetter(Class c){
        Method[] methods = c.getMethods();
        ArrayList<String> getter = new ArrayList<>();
        HashMap<String, Method> setter = new HashMap<>();
        for(int i=0; i<methods.length; i++){
            String name = methods[i].getName();
            if(name.startsWith("get"))
                getter.add(name);
            if(name.startsWith("set")){
                setter.put(name, methods[i]);
            }      
        }
        System.out.println("All of the get/set methods show below: ");
        for(int i=0; i<getter.size(); i++){
            System.out.println(getter.get(i));
        }
        for(String s : setter.keySet()){
            System.out.println(s);
        }
    } 
   
    public static void main(String[] args) throws ClassNotFoundException, Exception {
        new Reflection();
    }  
}

package readinsolar;

import java.io.*;
import java.util.*;

public class ReadinSolar {
    public static void main(String[] args) throws FileNotFoundException {
        new Sun();
        new Earth();
        new Venus();
        new Moon();
}
static class Sun{
    public Sun() throws FileNotFoundException{
        System.out.println("The information below is about Sun: ");
        Scanner s = new Scanner(new BufferedReader(new FileReader("solarsystem .dat")));
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        ArrayList<String> attributes1 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes2 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes3 = new ArrayList<String>();
        while(s.hasNext()){
            attributes3.add(s.next());
        }
        for(int j=0;j<10;j++){
            attributes1.set(j,attributes3.get(j+9));
            map.put(attributes2.get(j),attributes1.get(j)); 
        }   
        for (String key : map.keySet()){
            System.out.println(key+"→"+map.get(key));       
        }   
    }
}           

 static class Earth{
    public Earth() throws FileNotFoundException{
        System.out.println("The information below is about Earth: ");
        Scanner s = new Scanner(new BufferedReader(new FileReader("solarsystem .dat")));
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        ArrayList<String> attributes1 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes2 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes3 = new ArrayList<String>();
        while(s.hasNext()){
            attributes3.add(s.next());
        }
        for(int j=0;j<10;j++){
            attributes1.set(j,attributes3.get(j+39));
            map.put(attributes2.get(j),attributes1.get(j)); 
        }   
        for (String key : map.keySet()){
            System.out.println(key+"→"+map.get(key));       
        }   
    }
}
    
static class Venus{
    public Venus() throws FileNotFoundException{
        System.out.println("The information below is about Venus: ");
        Scanner s = new Scanner(new BufferedReader(new FileReader("solarsystem .dat")));
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        ArrayList<String> attributes1 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes2 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes3 = new ArrayList<String>();
        while(s.hasNext()){
            attributes3.add(s.next());
        }
        for(int j=0;j<10;j++){
            attributes1.set(j,attributes3.get(j+29));
            map.put(attributes2.get(j),attributes1.get(j)); 
        }   
        for (String key : map.keySet()){
            System.out.println(key+"→"+map.get(key));       
        }   
    }
}
static class Moon{
    public Moon() throws FileNotFoundException{
        System.out.println("The information below is about Moon: ");
        Scanner s = new Scanner(new BufferedReader(new FileReader("solarsystem .dat")));
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        ArrayList<String> attributes1 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes2 = new ArrayList<String>(Arrays.asList("Name","Orbits","Mass(kg)","Diam(m)","Perihelion(m)","Aphelion(m)","orbPeriod(days)","rotationalPeriod(hours)","axialtilt(deg)","orbinclin(deg)"));
        ArrayList<String> attributes3 = new ArrayList<String>();
        while(s.hasNext()){
            attributes3.add(s.next());
        }
        for(int j=0;j<10;j++){
            attributes1.set(j,attributes3.get(j+49));
            map.put(attributes2.get(j),attributes1.get(j)); 
        }   
        for (String key : map.keySet()){
            System.out.println(key+"→"+map.get(key));       
        }   
    }
}
}          



    
    


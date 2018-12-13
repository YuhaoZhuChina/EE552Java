/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

/**
 *
 * @author pro
 */
public class bean extends Object{
     private String FirstName;
        private String LastName;
        public bean(){
          
        }
        public void setFirstName(String name) {
            FirstName = name;
        }
        public String getFirstName() { 
            return FirstName; 
        }
        public void setLastName(String name) { 
            LastName = name;
        }
        public String getLastName() { 
            return LastName;
        }
        public String toString() { 
            if(LastName == null){
                 return "The FirstName is: "+ FirstName;
            }
              if(FirstName == null){
                 return "The LastName is: "+ LastName;
            }
              return null;
        }
}

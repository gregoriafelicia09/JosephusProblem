import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File(fileName));
         while(file.hasNextLine()){
            String name = file.nextLine().trim();
               add(name);
         }
         PersonNode cur = circle;
         //Find the last node in the list
         while(cur.next != null){
            cur = cur.next;
         }
         //store reference to the last node
         track = cur;
         track.next = circle;
         
         // generate, print, and save the random elimination count
         Random rand = new Random();
         eliminationCount = rand.nextInt(size/2) + 1;
         System.out.println("=== Elimination count is " + eliminationCount + " ===");
         

      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
     if (circle == null){
         circle = new PersonNode(val);
         size++;
     } else{
         PersonNode cur = circle;
     
         while(cur.next != null){
            cur = cur.next;
         }
     cur.next = new PersonNode(val);
     size++;
     }
   }
   
   public void eliminate() {
      // count to the elimination count
      
      for(int i = 0; i < eliminationCount - 1; i++){
         track = track.next;
      }
      // print who will be eliminated
      PersonNode toEliminate = track.next;
      System.out.println(toEliminate.name + " is eliminated");
       
      // eliminate the person and update "front" of the circle and size
      if (toEliminate == circle){
         circle = circle.next;
      }
      track.next = toEliminate.next;
      size--;
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      // print the remaining survivors (watch out for infinite loop since list is circular)
      if(isOver()){
         return circle.name + " is the last survivor";
      }
      
      StringBuilder sb = new StringBuilder("Remaining survivors: ");
      PersonNode cur = circle;
      
      for(int i = 1; i <= size; i++){
         if (i > 1){
         sb.append(", ");
         }
         sb.append(i).append("- " ).append(cur.name);
         cur = cur.next;
         
      }
      return sb.toString();
   }

}


/*
# PROGRAM OUTPUT

 ----jGRASP exec: java JosephusDriver
 === Elimination count is 3 ===
 Remaining survivors: 1- Muhammad, 2- Beza, 3- Ibrar, 4- Nur, 5- Krystal, 6- River, 7- Soham, 8- Leon, 9- Will, 10- Qiao
 
 Continue elimination? <press enter>
 
 Ibrar is eliminated
 Remaining survivors: 1- Muhammad, 2- Beza, 3- Nur, 4- Krystal, 5- River, 6- Soham, 7- Leon, 8- Will, 9- Qiao
 
 Continue elimination? <press enter>
 
 River is eliminated
 Remaining survivors: 1- Muhammad, 2- Beza, 3- Nur, 4- Krystal, 5- Soham, 6- Leon, 7- Will, 8- Qiao
 
 Continue elimination? <press enter>
 
 Will is eliminated
 Remaining survivors: 1- Muhammad, 2- Beza, 3- Nur, 4- Krystal, 5- Soham, 6- Leon, 7- Qiao
 
 Continue elimination? <press enter>
 
 Beza is eliminated
 Remaining survivors: 1- Muhammad, 2- Nur, 3- Krystal, 4- Soham, 5- Leon, 6- Qiao
 
 Continue elimination? <press enter>
 
 Soham is eliminated
 Remaining survivors: 1- Muhammad, 2- Nur, 3- Krystal, 4- Leon, 5- Qiao
 
 Continue elimination? <press enter>
 
 Muhammad is eliminated
 Remaining survivors: 1- Nur, 2- Krystal, 3- Leon, 4- Qiao
 
 Continue elimination? <press enter>
 
 Leon is eliminated
 Remaining survivors: 1- Nur, 2- Krystal, 3- Qiao
 
 Continue elimination? <press enter>
 
 Krystal is eliminated
 Remaining survivors: 1- Nur, 2- Qiao
 
 Continue elimination? <press enter>
 
 Qiao is eliminated
 Nur is the last survivor
 
  ----jGRASP: Operation complete.
 

*/
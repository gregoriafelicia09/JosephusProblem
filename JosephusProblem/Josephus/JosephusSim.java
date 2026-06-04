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
         // make the ring circular by attaching last node's next to front
         PersonNode cur = circle;
         while(cur.next != null){
            cur = cur.next;
         }
         // remember the last node as the one in front of the next to get eliminated
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
      
      for(int i = 0; i <= size; i++){
         if (i < 1){
         sb.append(", ");
         }
         sb.append(i).append("-" ).append(cur.name);
         cur = cur.next;
         
      }
      return sb.toString();
   }

}


/*
# PROGRAM OUTPUT

 ----jGRASP exec: java JosephusDriver
 === Elimination count is 3 ===
 Remaining survivors: , 0-Muhammad1-Beza2-Ibrar3-Nur4-Krystal5-River6-Soham7-Leon8-Will9-Qiao10-Muhammad
 
 Continue elimination? <press enter>
 
 Ibrar is eliminated
 Remaining survivors: , 0-Muhammad1-Beza2-Nur3-Krystal4-River5-Soham6-Leon7-Will8-Qiao9-Muhammad
 
 Continue elimination? <press enter>
 
 River is eliminated
 Remaining survivors: , 0-Muhammad1-Beza2-Nur3-Krystal4-Soham5-Leon6-Will7-Qiao8-Muhammad
 
 Continue elimination? <press enter>
 
 Will is eliminated
 Remaining survivors: , 0-Muhammad1-Beza2-Nur3-Krystal4-Soham5-Leon6-Qiao7-Muhammad
 
 Continue elimination? <press enter>
 
 Beza is eliminated
 Remaining survivors: , 0-Muhammad1-Nur2-Krystal3-Soham4-Leon5-Qiao6-Muhammad
 
 Continue elimination? <press enter>
 
 Soham is eliminated
 Remaining survivors: , 0-Muhammad1-Nur2-Krystal3-Leon4-Qiao5-Muhammad
 
 Continue elimination? <press enter>
 
 Muhammad is eliminated
 Remaining survivors: , 0-Nur1-Krystal2-Leon3-Qiao4-Nur
 
 Continue elimination? <press enter>
 
 Leon is eliminated
 Remaining survivors: , 0-Nur1-Krystal2-Qiao3-Nur
 
 Continue elimination? <press enter>
 
 Krystal is eliminated
 Remaining survivors: , 0-Nur1-Qiao2-Nur
 
 Continue elimination? <press enter>
 
 Qiao is eliminated
 Nur is the last survivor
 
  ----jGRASP: Operation complete.
 

*/
import java.util.*;

public class EditDistance {
  
  public static int EditDistance(String s, String t) {
    int[][] editDistance = new int[s.length()+1][t.length()+1];
    for(int i=0; i<s.length()+1; i++){
      editDistance[i][0] = i;
    }
    for(int i=1; i<t.length()+1; i++){
      editDistance[0][i] = i;
    }
    int toAdd = 1;
    int  min = 0;
    for(int i=1; i<s.length()+1; i++){
      for(int j=1; j<t.length()+1; j++){
        if(s.charAt(i-1) == t.charAt(j-1)){
          toAdd = 0;
        } else {
          toAdd = 1;
        }

        min = Math.min(editDistance[i-1][j] + 1, editDistance[i][j-1] + 1);
        min = Math.min(min, editDistance[i-1][j-1] + toAdd);
        editDistance[i][j] = min;
      }
    }
    
    return editDistance[s.length()][t.length()];
  }

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}

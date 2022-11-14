package soduku;

import java.util.Scanner;

/**
 *
 * @author bal-admin
 */
public class Soduku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] soduku = new int[9][9]; int userflag =1; 
        boolean game=true;
        Scanner input = new Scanner (System.in);
        
        initializeSoduku(soduku);   printSoduku(soduku);

        while (game = true && userflag !=0) {
            int flag; int flagIndex;
        
        System.out.println("Enter the value from 1 to 9"); //takes the input from user
        int number = input.nextInt();
        
        System.out.println("Enter the row index of board"); //takes the row index number from user
        int row = input.nextInt();
        
        System.out.println("Enter the column index of board"); //takes the column index number from user
         int column = input.nextInt();
         
            // Step 1 and 2: validate the input, row index and column index using a method
            // Step 3: Validates the move using method to check wether the input is duplicated in its row or column
            // Step 4: Validates the 3x3 boxes of soduku for duplication of any number
            
         if ((flag=validateNumber(number))==0){
            if ((flag=validateIndex(row, column))==0){
                validateMove(soduku, number, row, column);
                duplicationCheck(soduku, number, row, column);
            }
            else
            System.out.println("Please enter the valid index number");
         }
         else
            System.out.println("Please enter the valid number");


         printSoduku(soduku); //prints the soduku board 

         game = continueGame(soduku); //check either the board is solved or not

         System.out.println("Please enter 0 to exit"); //takes input from user to exit the game
         userflag = input.nextInt();
        }
    }
    
    public static void initializeSoduku(int[][] data){
        data[0][0]=5; data[0][1]=3; data[0][4]=7;
        data[1][0]=6; data[1][3]=1; data[1][4]=9; data[1][5]=5;
        data[2][1]=9; data[2][2]=8; data[2][7]=6;
        data[3][0]= 8; data[3][4]=6; data[3][8]=3; 
        data[4][0]=4; data[4][3]=8; data[4][5]=3; data[4][8]=1;
        data[5][0]= 7; data[5][4]=2; data[5][8]=6;
        data[6][1]=6;
        data[7][3]=4; data[7][4]=1;data[7][5]=9;data[7][8]=5;
        data[8][4]=8; data[8][7]=7; data[8][8]=9;
        
                
    }
    public static void printSoduku(int[][] data){
        for (int i=0; i < data.length;i++){
            for (int j=0; j < data[i].length;j++){
                System.out.print(data[i][j] + "\t");
                
            }
            System.out.println();
        }
    }
    
    public static void validateMove(int[][] data, int number, int row, int column){
       int flag =0;
       int duplication=0;
       
        
        for (int i =0; i < data.length; i++) {
            if (data[i][column] == number) {
                flag=-1;
            }
        }
        for (int j=0;j < data[row].length; j++){
            if (data[row][j] == number){
                flag =-1;
            }
        }

        if (flag != -1){
            if ((duplication = (duplicationCheck(data, number, row, column))) == -1){
                gameBoardCheck(data, number, row, column);
            }
            else
            System.out.println("The number is duplicated");
        }
        else
        System.out.println("The number is invalid at " + "[" + row + " , " + column + "]");
        


    }
    public static int duplicationCheck(int[][] data, int number, int row, int column){
        int flag = -1;
        int starting = row - (row % 3); int starting1 = column - (column % 3);
        for (int i= starting; i < starting+ 3; i++){
            for (int j= starting1; j < starting1+3;j++ )
             if(data[i][j] == number){
                flag=0;
             }
        }                                                                                           
        return flag;
    }

public static void gameBoardCheck(int[][] data, int number, int row, int column){
    int[][] originalboard = new int[9][9];
        initializeSoduku(originalboard);
    
    if (originalboard[row][column] == 0){
        data[row][column] = number;
        System.out.println("The board is updated");
    }
    else
    System.out.println("The predefined values can't be changed");
}
    public static boolean continueGame(int[][] data){
        int sum=0; boolean flag=true;
        for (int i=0; i < data.length;i++){
            for (int j=0; j < data[i].length;j++){
                sum += data[i][j];
            }
            if (sum == 405){
                flag=false;
            }
            
    }
        return flag;
}
    public static int validateNumber(int number){
    int flag=-1;
    if (number >=1 && number <=9){
        flag =0;
       }
       return flag;
    }

    public static int validateIndex(int row,int column){
        int flag=-1;

        if (row >=0 && row <=8){
            if (column >=0 && column <=8){
                flag =0;
            }
        }
        return flag;
    }
}

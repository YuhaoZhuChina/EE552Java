
/*Instruction: the initial figure read from txt file, and i create two txt files named matrix1 and matrix2. Besides, i set the loop time as 30, so you can change it that you want.*/
package game_of_life;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Game_of_Life extends JFrame{
    private int height = 1000;
    private int width = 1000;
    private JPanel gridPanel;
    private JTextField[][] matrix;
    private int[][] ini_matrix;
    private int col_count = 0;
    private int row_count = 0;
    private Container c;
    private int loop_times =30;
    private String file_name ="matrix2.txt";
    public Game_of_Life() throws IOException{
        super("GAME OF LIFE");
        c = getContentPane();
        setSize(height,width);
        ReadtheMatrix();
        for(int i=0; i<row_count; i++){
            for(int j=0; j<col_count; j++){
                System.out.print(ini_matrix[i][j]+" ");
            }
            System.out.println();
        }

        new Thread(new SwingControl()).start();
        setVisible(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void ShowMatrix(){
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(row_count,col_count));
        matrix = new JTextField[row_count][col_count];
        for(int i=0; i<row_count; i++)
            for(int j=0; j<col_count; j++){
                JTextField pin = new JTextField();
                if(ini_matrix[i][j]==1){
                    pin.setBackground(Color.black);
                }
                matrix[i][j] = pin;
                gridPanel.add(matrix[i][j]);
            }
        gridPanel.updateUI();
        c.add(gridPanel,BorderLayout.CENTER);
        setVisible(true);
        
    }
    public int[][] ReadtheMatrix() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(file_name));
        String line = null;
        while((line=br.readLine())!= null){
            row_count++;
            col_count = ((line.length()-1)/2)+1;
        }
        System.out.println(row_count+" "+col_count);
        br.close();
        int row = 0;
        BufferedReader br1 = new BufferedReader(new FileReader(file_name));
        String line1 = null;
        ini_matrix = new int[row_count][col_count];
        while((line1=br1.readLine())!=null){
            String[] str = line1.split("[ \\s+ ]");
            for(int i=0; i<str.length; i++){
                ini_matrix[row][i] = Integer.parseInt(str[i]);
            }
            row++;
        }
        br1.close();
        return ini_matrix;
    }
    public int FindLifeNum(int y, int x){ 
        int LifeNum = 0;
        //左边
        if(x!=0){
            LifeNum += ini_matrix[y][x-1];
        }
        //左上
        if(x!=0 && y!=0){
            LifeNum += ini_matrix[y-1][x-1];
        }
        //上边
        if(y!=0){
            LifeNum += ini_matrix[y-1][x];
        }
        //右上
        if((x!=(col_count-1)) && y!=0){
            LifeNum += ini_matrix[y-1][x+1];
        }
        //右边
        if(x!=(col_count-1)){
            LifeNum += ini_matrix[y][x+1];
        }
        //右下
        if((x!=(col_count-1)) && y!=(row_count-1)){
            LifeNum += ini_matrix[y+1][x+1];
        }
        //下边
        if(y!=(row_count-1)){
            LifeNum += ini_matrix[y+1][x];
        }
        //左下
        if(x!=0 && y!=(row_count-1) ){
            LifeNum += ini_matrix[y+1][x-1];
        }
        return LifeNum;
    }
    public void TransFormer(){
        int[][] tranform_matrix = new int[row_count][col_count];
        for(int i=0; i<row_count; i++){
            for(int j=0; j<col_count; j++){
                int num = FindLifeNum(i,j);
                if(num==3){
                    tranform_matrix[i][j] = 1;
                }
                if(num==2){
                    tranform_matrix[i][j] = ini_matrix[i][j];
                }
            }
        }
        ini_matrix = tranform_matrix;
    }
    public class SwingControl implements Runnable{
        @Override
        public void run(){
            while(loop_times>0){
                ShowMatrix();
                TransFormer();
                loop_times--;
            try{
                TimeUnit.MILLISECONDS.sleep(500);
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game_of_Life.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }
    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        new Game_of_Life();
        //对于周围活着的细胞为3的情况，下一状态该细胞总是活的
        //对于周围活着的细胞为2的情况，下一状态与上一状态相同
    }
}

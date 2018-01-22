/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

/**
 *
 * @author archit
 */
import java.util.*;
import javax.swing.*;
public class ChessGame {
       static String chessBoard[][]={
        {"r","k","b","q","a","b","k","r"},
        {"p","p","p","p","p","p","p","p"},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"P","P","P","P","P","P","P","P"},
        {"R","K","B","Q","A","B","K","R"}};
        static int KingPositionC, KingPositionL;
        static int turn=0;
        
    public static void main(String[] args) {
        while (!"A".equals(chessBoard[KingPositionC/8][KingPositionC%8])) {KingPositionC++;}//get King's location
        while (!"a".equals(chessBoard[KingPositionL/8][KingPositionL%8])) {KingPositionL++;}//get king's location
JFrame f=new JFrame("Chess Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface2 ui=new UserInterface2();
        f.add(ui);
        f.setSize(258, 275);
        f.setVisible(true);

        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
       
    }
   
public static String possibleMoves(){
        String list="";
        for(int i=0;i<64;i++){
            switch (chessBoard[i/8][i%8]){
                case "P":list+=possibleP(i);
                    break;
                case "p": list+=possiblep(i);
                    break;
                case "R": list+=possibleR(i);
                    break;
                case "r": list+=possibler(i);
                    break;    
                case "K": list+=possibleK(i);
                    break;
                case "k": list+=possiblek(i);
                    break;
                case "B": list+=possibleB(i);
                    break;
                case "b": list+=possibleb(i);
                    break;    
                case "Q": list+=possibleQ(i);
                    break;
                case "q": list+=possibleq(i);
                    break;
                case "A": list+=possibleA(i);
                    break;
                case "a": list+=possiblea(i);
                    break;
            }
        }
        return list;//x1,y1,x2,y2 captured piece
    }
public static String possibleP(int i){
 String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=-1; j<=1; j+=2) {
            try {//capture
                if (Character.isLowerCase(chessBoard[r-1][c+j].charAt(0)) && i>=16) {
                    oldPiece=chessBoard[r-1][c+j];
                    chessBoard[r][c]=" ";
                    chessBoard[r-1][c+j]="P";
                    if (WhiteKingSafe()) {
                        list=list+r+c+(r-1)+(c+j)+oldPiece;
                    }
                    chessBoard[r][c]="P";
                    chessBoard[r-1][c+j]=oldPiece;
                }
            } catch (Exception e) {}
            try {//promotion && capture
                if (Character.isLowerCase(chessBoard[r-1][c+j].charAt(0)) && i<16) {
                    String[] temp={"Q","R","B","K"};
                    for (int k=0; k<4; k++) {
                        oldPiece=chessBoard[r-1][c+j];
                        chessBoard[r][c]=" ";
                        chessBoard[r-1][c+j]=temp[k];
                        if (WhiteKingSafe()) {
                            //column1,column2,captured-piece,new-piece,P
                            list=list+c+(c+j)+oldPiece+temp[k]+"Z";
                        }
                        chessBoard[r][c]="P";
                        chessBoard[r-1][c+j]=oldPiece;
                    }
                }
            } catch (Exception e) {}
        }
        try {//move one up
            if (" ".equals(chessBoard[r-1][c]) && i>=16) {
                oldPiece=chessBoard[r-1][c];
                chessBoard[r][c]=" ";
                chessBoard[r-1][c]="P";
                if (WhiteKingSafe()) {
                    list=list+r+c+(r-1)+c+oldPiece;
                }
                chessBoard[r][c]="P";
                chessBoard[r-1][c]=oldPiece;
            }
        } catch (Exception e) {}
        try {//promotion && no capture
            if (" ".equals(chessBoard[r-1][c]) && i<16) {
                String[] temp={"Q","R","B","K"};
                for (int k=0; k<4; k++) {
                    oldPiece=chessBoard[r-1][c];
                    chessBoard[r][c]=" ";
                    chessBoard[r-1][c]=temp[k];
                    if (WhiteKingSafe()) {
                        //column1,column2,captured-piece,new-piece,P
                        list=list+c+c+oldPiece+temp[k]+"Z";
                    }
                    chessBoard[r][c]="P";
                    chessBoard[r-1][c]=oldPiece;
                }
            }
        } catch (Exception e) {}
        try {//move two up
            if (" ".equals(chessBoard[r-1][c]) && " ".equals(chessBoard[r-2][c]) && i>=48) {
                oldPiece=chessBoard[r-2][c];
                chessBoard[r][c]=" ";
                chessBoard[r-2][c]="P";
                if (WhiteKingSafe()) {
                    list=list+r+c+(r-2)+c+oldPiece;
                }
                chessBoard[r][c]="P";
                chessBoard[r-2][c]=oldPiece;
            }
        } catch (Exception e) {}
        return list;
    }

public static String possiblep(int i){
 String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=-1; j<=1; j+=2) {
            try {//capture
                if (Character.isUpperCase(chessBoard[r+1][c+j].charAt(0)) && i<=48) {
                    oldPiece=chessBoard[r+1][c+j];
                    chessBoard[r][c]=" ";
                    chessBoard[r+1][c+j]="p";
                    if (BlackKingSafe()) {
                        list=list+r+c+(r+1)+(c+j)+oldPiece;
                    }
                    chessBoard[r][c]="p";
                    chessBoard[r+1][c+j]=oldPiece;
                }
            } catch (Exception e) {}
            try {//promotion && capture
                if (Character.isUpperCase(chessBoard[r+1][c+j].charAt(0)) && i>48) {
                    String[] temp={"q","r","b","k"};
                    for (int k=0; k<4; k++) {
                        oldPiece=chessBoard[r+1][c+j];
                        chessBoard[r][c]=" ";
                        chessBoard[r+1][c+j]=temp[k];
                        if (BlackKingSafe()) {
                            //column1,column2,captured-piece,new-piece,P
                            list=list+c+(c+j)+oldPiece+temp[k]+"z";
                        }
                        chessBoard[r][c]="p";
                        chessBoard[r+1][c+j]=oldPiece;
                    }
                }
            } catch (Exception e) {}
        }
        try {//move one up
            if (" ".equals(chessBoard[r+1][c]) && i<=48) {
                oldPiece=chessBoard[r+1][c];
                chessBoard[r][c]=" ";
                chessBoard[r+1][c]="p";
                if (BlackKingSafe()) {
                    list=list+r+c+(r+1)+c+oldPiece;
                }
                chessBoard[r][c]="p";
                chessBoard[r+1][c]=oldPiece;
            }
        } catch (Exception e) {}
        try {//promotion && no capture
            if (" ".equals(chessBoard[r+1][c]) && i>48) {
                String[] temp={"q","r","b","k"};
                for (int k=0; k<4; k++) {
                    oldPiece=chessBoard[r+1][c];
                    chessBoard[r][c]=" ";
                    chessBoard[r+1][c]=temp[k];
                    if (BlackKingSafe()) {
                        //column1,column2,captured-piece,new-piece,P
                        list=list+c+c+oldPiece+temp[k]+"z";
                    }
                    chessBoard[r][c]="p";
                    chessBoard[r+1][c]=oldPiece;
                }
            }
        } catch (Exception e) {}
        try {//move two up
            if (" ".equals(chessBoard[r+1][c]) && " ".equals(chessBoard[r+2][c]) && i<=16) {
                oldPiece=chessBoard[r+2][c];
                chessBoard[r][c]=" ";
                chessBoard[r+2][c]="p";
                if (BlackKingSafe()) {
                    list=list+r+c+(r+2)+c+oldPiece;
                }
                chessBoard[r][c]="p";
                chessBoard[r+2][c]=oldPiece;
            }
        } catch (Exception e) {}
        return list;
    }
  
    public static String possibleK(int i){
String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=-1; j<=1; j+=2) {
            for (int k=-1; k<=1; k+=2) {
                try {
                    if (Character.isLowerCase(chessBoard[r+j][c+k*2].charAt(0)) || " ".equals(chessBoard[r+j][c+k*2])) {
                        oldPiece=chessBoard[r+j][c+k*2];
                        chessBoard[r][c]=" ";
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r+j)+(c+k*2)+oldPiece;
                        }
                        chessBoard[r][c]="K";
                        chessBoard[r+j][c+k*2]=oldPiece;
                    }
                } catch (Exception e) {}
                try {
                    if (Character.isLowerCase(chessBoard[r+j*2][c+k].charAt(0)) || " ".equals(chessBoard[r+j*2][c+k])) {
                        oldPiece=chessBoard[r+j*2][c+k];
                        chessBoard[r][c]=" ";
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r+j*2)+(c+k)+oldPiece;
                        }
                        chessBoard[r][c]="K";
                        chessBoard[r+j*2][c+k]=oldPiece;
                    }
                } catch (Exception e) {}
            }
        }
        return list;
    }
    public static String possiblek(int i){
String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=-1; j<=1; j+=2) {
            for (int k=-1; k<=1; k+=2) {
                try {
                    if (Character.isUpperCase(chessBoard[r+j][c+k*2].charAt(0)) || " ".equals(chessBoard[r+j][c+k*2])) {
                        oldPiece=chessBoard[r+j][c+k*2];
                        chessBoard[r][c]=" ";
                        if (BlackKingSafe()) {
                            list=list+r+c+(r+j)+(c+k*2)+oldPiece;
                        }
                        chessBoard[r][c]="k";
                        chessBoard[r+j][c+k*2]=oldPiece;
                    }
                } catch (Exception e) {}
                try {
                    if (Character.isUpperCase(chessBoard[r+j*2][c+k].charAt(0)) || " ".equals(chessBoard[r+j*2][c+k])) {
                        oldPiece=chessBoard[r+j*2][c+k];
                        chessBoard[r][c]=" ";
                        if (BlackKingSafe()) {
                            list=list+r+c+(r+j*2)+(c+k)+oldPiece;
                        }
                        chessBoard[r][c]="k";
                        chessBoard[r+j*2][c+k]=oldPiece;
                    }
                } catch (Exception e) {}
            }
        }
        return list;
    }
    public static String possibleR(int i){
String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j+=2) {
            try {
                while(" ".equals(chessBoard[r][c+temp*j]))
                {
                    oldPiece=chessBoard[r][c+temp*j];
                    chessBoard[r][c]=" ";
                    chessBoard[r][c+temp*j]="R";
                    if (WhiteKingSafe()) {
                        list=list+r+c+r+(c+temp*j)+oldPiece;
                    }
                    chessBoard[r][c]="R";
                    chessBoard[r][c+temp*j]=oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(chessBoard[r][c+temp*j].charAt(0))) {
                    oldPiece=chessBoard[r][c+temp*j];
                    chessBoard[r][c]=" ";
                    chessBoard[r][c+temp*j]="R";
                    if (WhiteKingSafe()) {
                        list=list+r+c+r+(c+temp*j)+oldPiece;
                    }
                    chessBoard[r][c]="R";
                    chessBoard[r][c+temp*j]=oldPiece;
                }
            } catch (Exception e) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[r+temp*j][c]))
                {
                    oldPiece=chessBoard[r+temp*j][c];
                    chessBoard[r][c]=" ";
                    chessBoard[r+temp*j][c]="R";
                    if (WhiteKingSafe()) {
                        list=list+r+c+(r+temp*j)+c+oldPiece;
                    }
                    chessBoard[r][c]="R";
                    chessBoard[r+temp*j][c]=oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(chessBoard[r+temp*j][c].charAt(0))) {
                    oldPiece=chessBoard[r+temp*j][c];
                    chessBoard[r][c]=" ";
                    chessBoard[r+temp*j][c]="R";
                    if (WhiteKingSafe()) {
                        list=list+r+c+(r+temp*j)+c+oldPiece;
                    }
                    chessBoard[r][c]="R";
                    chessBoard[r+temp*j][c]=oldPiece;
                }
            } catch (Exception e) {}
            temp=1;
        }
        return list;
    }
        public static String possibler(int i){
String list=" ", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j+=2) {
            try {
                while(" ".equals(chessBoard[r][c+temp*j]))
                {
                    oldPiece=chessBoard[r][c+temp*j];
                    chessBoard[r][c]=" ";
                    chessBoard[r][c+temp*j]="r";
                    if (BlackKingSafe()) {
                        list=list+r+c+r+(c+temp*j)+oldPiece;
                    }
                    chessBoard[r][c]="r";
                    chessBoard[r][c+temp*j]=oldPiece;
                    temp++;
                }
                if (Character.isUpperCase(chessBoard[r][c+temp*j].charAt(0))) {
                    oldPiece=chessBoard[r][c+temp*j];
                    chessBoard[r][c]=" ";
                    chessBoard[r][c+temp*j]="r";
                    if (BlackKingSafe()) {
                        list=list+r+c+r+(c+temp*j)+oldPiece;
                    }
                    chessBoard[r][c]="r";
                    chessBoard[r][c+temp*j]=oldPiece;
                }
            } catch (Exception e) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[r+temp*j][c]))
                {
                    oldPiece=chessBoard[r+temp*j][c];
                    chessBoard[r][c]=" ";
                    chessBoard[r+temp*j][c]="r";
                    if (BlackKingSafe()) {
                        list=list+r+c+(r+temp*j)+c+oldPiece;
                    }
                    chessBoard[r][c]="r";
                    chessBoard[r+temp*j][c]=oldPiece;
                    temp++;
                }
                if (Character.isUpperCase(chessBoard[r+temp*j][c].charAt(0))) {
                    oldPiece=chessBoard[r+temp*j][c];
                    chessBoard[r][c]=" ";
                    chessBoard[r+temp*j][c]="r";
                    if (BlackKingSafe()) {
                        list=list+r+c+(r+temp*j)+c+oldPiece;
                    }
                    chessBoard[r][c]="r";
                    chessBoard[r+temp*j][c]=oldPiece;
                }
            } catch (Exception e) {}
            temp=1;
        }
        return list;
    }
    public static String possibleB(int i){
         String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j+=2) {
            for (int k=-1; k<=1; k+=2) {
                try {
                    while(" ".equals(chessBoard[r+temp*j][c+temp*k]))
                    {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="B";
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="B";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                        temp++;
                    }
                    if (Character.isLowerCase(chessBoard[r+temp*j][c+temp*k].charAt(0))) {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="B";
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="B";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                    }
                } catch (Exception e) {}
                temp=1;
            } 
        }
            return list;
    }
        public static String possibleb(int i){
         String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j+=2) {
            for (int k=-1; k<=1; k+=2) {
                try {
                    while(" ".equals(chessBoard[r+temp*j][c+temp*k]))
                    {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="b";
                        if (BlackKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="b";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                        temp++;
                    }
                    if (Character.isUpperCase(chessBoard[r+temp*j][c+temp*k].charAt(0))) {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="b";
                        if (BlackKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="b";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                    }
                } catch (Exception e) {}
                temp=1;
            } 
        }
            return list;
    }
    public static String possibleQ(int i){
        String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j++) {
            for (int k=-1; k<=1; k++) {
                if(j!=0||k!=0){
                try {
                    while(" ".equals(chessBoard[r+temp*j][c+temp*k]))
                    {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="Q";
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="Q";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                        temp++;
                    }
                    if (Character.isLowerCase(chessBoard[r+temp*j][c+temp*k].charAt(0))) {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="Q";
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="Q";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                    }
                } catch (Exception e) {}
                temp=1;
                }
            }
        }
        return list;
    }
    public static String possibleq(int i){
        String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j++) {
            for (int k=-1; k<=1; k++) {
                if(j!=0||k!=0){
                try {
                    while(" ".equals(chessBoard[r+temp*j][c+temp*k]))
                    {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="q";
                        if (BlackKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="q";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                        temp++;
                    }
                    if (Character.isUpperCase(chessBoard[r+temp*j][c+temp*k].charAt(0))) {
                        oldPiece=chessBoard[r+temp*j][c+temp*k];
                        chessBoard[r][c]=" ";
                        chessBoard[r+temp*j][c+temp*k]="q";
                        if (BlackKingSafe()) {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        chessBoard[r][c]="q";
                        chessBoard[r+temp*j][c+temp*k]=oldPiece;
                    }
                } catch (Exception e) {}
                temp=1;
                }
            }
        }
        return list;
    }
     public static String possibleA(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=0; j<9; j++) {
            if (j!=4) {
                try {
                    if (Character.isLowerCase(chessBoard[r-1+j/3][c-1+j%3].charAt(0)) || " ".equals(chessBoard[r-1+j/3][c-1+j%3])) {
                        oldPiece=chessBoard[r-1+j/3][c-1+j%3];
                        chessBoard[r][c]=" ";
                        chessBoard[r-1+j/3][c-1+j%3]="A";
                        int WhiteKingTemp=KingPositionC;
                        KingPositionC=i+(j/3)*8+j%3-9;
                        if (WhiteKingSafe()) {
                            list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
                        }
                        chessBoard[r][c]="A";
                        chessBoard[r-1+j/3][c-1+j%3]=oldPiece;
                        KingPositionC=WhiteKingTemp;
                    }
                } catch (Exception e) {}
            }
        }
        //need to add casting later
        return list;
    }
       public static String possiblea(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=0; j<9; j++) {
            if (j!=4) {
                try {
                    if (Character.isUpperCase(chessBoard[r-1+j/3][c-1+j%3].charAt(0)) || " ".equals(chessBoard[r-1+j/3][c-1+j%3])) {
                        oldPiece=chessBoard[r-1+j/3][c-1+j%3];
                        chessBoard[r][c]=" ";
                        chessBoard[r-1+j/3][c-1+j%3]="a";
                        int BlackKingTemp=KingPositionL;
                        KingPositionL=i+(j/3)*8+j%3-9;
                        if (BlackKingSafe()) {
                            list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
                        }
                        chessBoard[r][c]="a";
                        chessBoard[r-1+j/3][c-1+j%3]=oldPiece;
                        KingPositionL=BlackKingTemp;
                    }
                } catch (Exception e) {}
            }
        }
        return list;
     
    }
      public static int rating() {
        return 0;
    }
        public static boolean WhiteKingSafe(){
        //bishop/queen
        int temp=1;
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try {
                    while(" ".equals(chessBoard[KingPositionC/8+temp*i][KingPositionC%8+temp*j])) {temp++;}
                    if ("b".equals(chessBoard[KingPositionC/8+temp*i][KingPositionC%8+temp*j]) ||
                            "q".equals(chessBoard[KingPositionC/8+temp*i][KingPositionC%8+temp*j])) {
                        return false;
                    }
                } catch (Exception e) {}
                temp=1;
            }
        }
        //rook/queen
        for (int i=-1; i<=1; i+=2) {
            try {
                while(" ".equals(chessBoard[KingPositionC/8][KingPositionC%8+temp*i])) {temp++;}
                if ("r".equals(chessBoard[KingPositionC/8][KingPositionC%8+temp*i]) ||
                        "q".equals(chessBoard[KingPositionC/8][KingPositionC%8+temp*i])) {
                    return false;
                }
            } catch (Exception e) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[KingPositionC/8+temp*i][KingPositionC%8])) {temp++;}
                if ("r".equals(chessBoard[KingPositionC/8+temp*i][KingPositionC%8]) ||
                        "q".equals(chessBoard[KingPositionC/8+temp*i][KingPositionC%8])) {
                    return false;
                }
            } catch (Exception e) {}
            temp=1;
        }
 //knight
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try {
                    if ("k".equals(chessBoard[KingPositionC/8+i][KingPositionC%8+j*2])) {
                        return false;
                    }
                } catch (Exception e) {}
                try {
                    if ("k".equals(chessBoard[KingPositionC/8+i*2][KingPositionC%8+j])) {
                        return false;
                    }
                } catch (Exception e) {}
            }
        }
        //pawn
        if (KingPositionC>=16) {
            try {
                if ("p".equals(chessBoard[KingPositionC/8-1][KingPositionC%8-1])) {
                    return false;
                }
            } catch (Exception e) {}
            try {
                if ("p".equals(chessBoard[KingPositionC/8-1][KingPositionC%8+1])) {
                    return false;
                }
            } catch (Exception e) {}
            
        }
            //king
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if (i!=0 || j!=0) {
                        try {
                            if ("a".equals(chessBoard[KingPositionC/8+i][KingPositionC%8+j])) {
                                return false;
                            }
                        } catch (Exception e) {}
                    }
                }
            }
        
        
        return true;
    }
        public static boolean BlackKingSafe(){
        //bishop/queen
        int temp=1;
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try {
                    while(" ".equals(chessBoard[KingPositionL/8+temp*i][KingPositionL%8+temp*j])) {temp++;}
                    if ("B".equals(chessBoard[KingPositionL/8+temp*i][KingPositionL%8+temp*j]) ||
                            "Q".equals(chessBoard[KingPositionL/8+temp*i][KingPositionL%8+temp*j])) {
                        return false;
                    }
                } catch (Exception e) {}
                temp=1;
            }
        }
        //rook/queen
        for (int i=-1; i<=1; i+=2) {
            try {
                while(" ".equals(chessBoard[KingPositionL/8][KingPositionL%8+temp*i])) {temp++;}
                if ("R".equals(chessBoard[KingPositionL/8][KingPositionL%8+temp*i]) ||
                        "Q".equals(chessBoard[KingPositionL/8][KingPositionL%8+temp*i])) {
                    return false;
                }
            } catch (Exception e) {}
            temp=1;
            try {
                while(" ".equals(chessBoard[KingPositionL/8+temp*i][KingPositionL%8])) {temp++;}
                if ("R".equals(chessBoard[KingPositionL/8+temp*i][KingPositionL%8]) ||
                        "Q".equals(chessBoard[KingPositionL/8+temp*i][KingPositionL%8])) {
                    return false;
                }
            } catch (Exception e) {}
            temp=1;
        }
 //knight
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try {
                    if ("K".equals(chessBoard[KingPositionL/8+i][KingPositionL%8+j*2])) {
                        return false;
                    }
                } catch (Exception e) {}
                try {
                    if ("K".equals(chessBoard[KingPositionL/8+i*2][KingPositionL%8+j])) {
                        return false;
                    }
                } catch (Exception e) {}
            }
        }
        //pawn
        if (KingPositionL<=48) {
            try {
                if ("P".equals(chessBoard[KingPositionL/8+1][KingPositionL%8-1])) {
                    return false;
                }
            } catch (Exception e) {}
            try {
                if ("P".equals(chessBoard[KingPositionL/8+1][KingPositionL%8+1])) {
                    return false;
                }
            } catch (Exception e) {}
            
        }
            //king
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if (i!=0 || j!=0) {
                        try {
                            if ("A".equals(chessBoard[KingPositionL/8+i][KingPositionL%8+j])) {
                                return false;
                            }
                        } catch (Exception e) {}
                    }
                }
            }
        
        
        return true;
    }
    public static void makeMove(String move) {
        System.out.println("In makeMove");
        System.out.println(move);
        
        String movedPiece;
        movedPiece = chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
        if (turn == 0) {
            // White's turn
            if(Character.isLowerCase(movedPiece.charAt(0))) {
                return;
            }
        }
        if (turn == 1) {
            // Black's turn
            if(Character.isUpperCase(movedPiece.charAt(0))) {
                return;
            }
        }
        turn = 1 - turn;
                
        if (move.charAt(4) != 'Z' && move.charAt(4) != 'z') {
            System.out.println("non promotion");
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=" ";
            if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
                KingPositionC=8*Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3));
            }
            if ("a".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
                KingPositionL=8*Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3));
            }
        } else {
            //if pawn promotion
            if(move.charAt(4)=='Z') {
                chessBoard[1][Character.getNumericValue(move.charAt(0))]=" ";
                chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(3));
            }
            if(move.charAt(4)=='z') {
                chessBoard[6][Character.getNumericValue(move.charAt(0))]=" ";
                chessBoard[7][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(3));
            }
        }
        
    }
   
}
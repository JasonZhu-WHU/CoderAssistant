package io.github.vcuswimlab.stackintheflow.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LexicalAnalyzer {
    /*
     * 1表示关键字
     * 2表示标识符
     * 3表示常数
     * 4表示运算符
     * 5表示界符
     * 6表示字符串
     * */

    //运算符
    static String []operation={"+","-","*","/","%","++","--","-=","*=","/=","&","|","^","~","<<",">>",">>>","==","!=",
            ">","<","=",">=","<=","&&","||","!","."};
    //界符
    static String []symbol={",",";",":","(",")","{","}","[","]","\""};

    static HashSet<String> operations=new HashSet<>();
    static HashSet<String> symbols=new HashSet<>();
    static {
        Collections.addAll(operations, operation);
        Collections.addAll(symbols, symbol);
    }
    //指向当前所读到字符串的位置的指针
    static int p;
    StringBuilder sb=new StringBuilder();

    public static void main(String []args) throws FileNotFoundException {
        File file=new File("src/com/Hello.java");
        LexicalAnalyzer lx = new LexicalAnalyzer();

        try(Scanner input=new Scanner(file)) {
            while (input.hasNextLine()){
                String str=input.nextLine();
                lx.analyze(str);
            }
        }
        System.out.println(lx.sb);
    }

    public void analyze(String str){
        p=0;
        char ch;
        str=str.trim();
        for (;p<str.length();p++){
            ch=str.charAt(p);
            if (Character.isDigit(ch)){
                digitCheck(str);
            }else if (Character.isLetter(ch)||ch=='_'){
                letterCheck(str);
            }else if (ch=='"'){
                stringCheck(str);
            }
            else if (ch==' '){
            }else {
                symbolCheck(str);
            }
        }
    }

    /*数字的识别
     * 1、识别退出：
     *   1.1、遇到空格符
     *   1.2、遇到运算符或者界符
     * 2、错误情况：
     *   2.1、两个及以上小数点
     *   2.2、掺杂字母
     * */
    public void digitCheck(String str){
        StringBuilder token= new StringBuilder(String.valueOf(str.charAt(p++)));
        //判断数字的小数点是否有且是否大于1
        int flag=0;
        boolean err=false;
        char ch;
        for (;p<str.length();p++) {
            ch = str.charAt(p);
            if (ch==' '||(!Character.isLetterOrDigit(ch)&&ch!='.')) {
                break;
            }else if (err){
                token.append(ch);
            }
            else {
                token.append(ch);
                if (ch == '.') {
                    if (flag == 1) {
                        err = true;
                    } else {
                        flag++;
                    }
                }else if (Character.isLetter(ch)){
                    err=true;
                }
            }
        }

        System.out.println("("+3+","+token+")");
        sb.append(token).append(" ");

        if (p!=str.length()-1||(p==str.length()-1&&!Character.isDigit(str.charAt(p)))){
            p--;
        }
    }

    //标识符，关键字的识别
    public void letterCheck(String str){
        StringBuilder token= new StringBuilder(String.valueOf(str.charAt(p++)));
        char ch;
        for (;p<str.length();p++){
            ch=str.charAt(p);
            if (!Character.isLetterOrDigit(ch)&&ch!='_'){
                break;
            }else{
                token.append(ch);
            }
        }

        System.out.println("("+2+","+token+")");
        sb.append(token).append(" ");

        if (p!=str.length()-1||(p==str.length()-1&&(!Character.isLetterOrDigit(str.charAt(p))&&str.charAt(p)!='_'))){
            p--;
        }
    }

    //符号的识别
    public void symbolCheck(String str){
        String token= String.valueOf(str.charAt(p++));
        char ch;
        if (symbols.contains(token)){
            System.out.println("("+5+","+token+")");
            sb.append(token).append(" ");
            p--;
        }else {
            if (operations.contains(token)){
                if (p<str.length()){
                    ch=str.charAt(p);
                    if (operations.contains(token+ch)){
                        token+=ch;
                        p++;
                        if (p<str.length()){
                            ch=str.charAt(p);
                            if (operations.contains(token+ch)){
                                token+=ch;
                            }else{
                                p--;
                            }
                        }
                    }else {
                        p--;
                    }
                    System.out.println("("+4+","+token+")");
                    sb.append(token).append(" ");
                }
            }else {
                p--;
//                System.out.println(lines+"line"+": "+token+" is wrong");
            }
        }
    }

    //字符串检查
    public void stringCheck(String str){
        StringBuilder token= new StringBuilder(String.valueOf(str.charAt(p++)));
        char ch;
        for (;p<str.length();p++){
            ch=str.charAt(p);
            token.append(ch);
            if (ch=='"'){
                break;
            }
        }
        System.out.println("("+6+","+token+")");
        sb.append(token).append(" ");

    }
}



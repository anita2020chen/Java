package day01;

public class calculateStringApp {
    public static void main(String[] args) {
        String str="1+2+3";
        calculateString cal=new calculateString();
        Integer res=cal.parseStrCounter(str);
        System.out.println(str+"的计算结果为"+res);
    }
}

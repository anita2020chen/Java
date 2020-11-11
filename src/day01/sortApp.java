package day01;

public class sortApp {
    public static void main(String[] args) {
        int[] array = new int[]{9, 3, 5, 4, 8, 6, 1};
        sort ss=new sort();
        int[] sortArray=ss.sortA(array);
        System.out.print("排序后的数组是：");
        for (int i = 0; i < sortArray.length; i++) {
            System.out.print(" "+sortArray[i]);
        }
    }
}

import java.util.LinkedList;

public class Test {
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < 100; i++){
            list.add(i);
        }

        for(int num : list){
            System.out.println(num);
        }
    }
}

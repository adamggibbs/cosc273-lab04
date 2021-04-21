import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class CombineTask implements Runnable{

    //private ConcurrentHashMap<Integer, LinkedList<Integer>> chunks;
    private ConcurrentHashMap<Integer, int[]> chunks;
    //private int[][] chunks;
    private int[] primes;
    private int index;

    public CombineTask(int[] primes, ConcurrentHashMap<Integer, int[]> chunks, final int[] small_primes){
        this.primes = primes;
        this.chunks = chunks;
        this.index = 0;

        chunks.put(0, small_primes);
    }

    public void run(){

        Integer id = 0;
        while(true){
            if(Thread.currentThread().isInterrupted()){
                break;
            }
            if(chunks.containsKey(id)){
                int[] the_primes = chunks.get(id);

                int i = 0;
                do {
                    primes[index] = the_primes[i];
                    index++;
                    i++;
                } while(!(i>=the_primes.length) && the_primes[i] != 0);
                id = Integer.valueOf(id.intValue() + 1);
            }
        }   

    } // method run()

} // class CombineTask

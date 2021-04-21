//import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class PrimeTask implements Runnable{
    
    private final int id;
    private final int chunk_size;
    private final int start;
    private final int[] small_primes;
    private ConcurrentHashMap<Integer, int[]> chunks;

    public PrimeTask(int id, final int chunk_size, int start, final int[] small_primes, ConcurrentHashMap<Integer, int[]> chunks){
        
        this.id = id;
        this.chunk_size = chunk_size;
        this.start = start;
        this.small_primes = small_primes;
        this.chunks = chunks;

    }

    public void run(){
        
            boolean[] isPrime = new boolean[chunk_size];

            for(int prime : small_primes){
                if(prime == 2){
                    continue;
                }
                int init = (int) Math.ceil((double) start / prime) * prime;
                for(int i = init; i < start + chunk_size; i += prime){
                    isPrime[i-start] = true;
                }
            }
    
            int[] primes = new int[chunk_size];
            
            int count = 0;

            int init;
            if(start%2 == 0){
                init = start + 1;
            } else {
                init = start;
            }
            for (int i = init; i < start + chunk_size; i+=2) {
                if (!isPrime[i-start]) {
                    primes[count] = i;
                    count++;
                }
            }

        chunks.put(id, primes);
        
    }

}
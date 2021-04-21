import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelPrimes {
	

    // the maximum prime number considered
    public static final int MAX = 350_000_000;
    //public static final int MAX = Integer.MAX_VALUE - 2;
    
    public static final int ROOT_MAX = (int) Math.sqrt(MAX);

    // the maximum number of primes less than MAX
    public static final int N_PRIMES = (int) (1.2 * MAX / Math.log(MAX));

    // time to run experiment
    public static final int TIMEOUT_MS = 1000;
    

    private static int countPrimes (int[] primes) {

		int[] truePrimes = Primes.getPrimesUpTo(MAX);
		
		for (int i = 0; i < truePrimes.length; ++i) {
			if (primes[i] != truePrimes[i]) {
				return i;
			}
		}

		return truePrimes.length;

    }
    
    public static void main (String[] args) {

		System.out.println(N_PRIMES);

		// create a thread pool
		int nThreads = Runtime.getRuntime().availableProcessors();
		//System.out.println(nThreads);
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);
		
		long start = System.currentTimeMillis();


		int[] primes = new int[N_PRIMES];
		ConcurrentHashMap<Integer, int[]> chunks = new ConcurrentHashMap<>();
		final int chunk_size = 100_000;

		final int[] small_primes = Primes.getPrimesUpTo(ROOT_MAX);
		CombineTask combiner = new CombineTask(primes, chunks, small_primes);
		pool.execute(combiner);
		
		int id = 1;
		for(int i = ROOT_MAX; i < MAX; i+=chunk_size){
			PrimeTask task;
			if(i+chunk_size > MAX){
				task = new PrimeTask(id, MAX - i, i, small_primes, chunks);
			} else {
				task = new PrimeTask(id, chunk_size, i, small_primes, chunks);
			}
			pool.execute(task);
			id++;
		}	

		// Don't modify the code below here
		
		long current = System.currentTimeMillis();

		try {
			Thread.sleep(TIMEOUT_MS - (current - start));
		} catch(InterruptedException e) {
			
		}

		pool.shutdownNow();

		long stop = System.currentTimeMillis();		

		int count = countPrimes(primes);

		if (count > 0) {
			System.out.println("Computed " + count + " primes, the largest being " + primes[count - 1] + ".");
		} else {
			System.out.println("Failed to produce any primes!");
		}
		
		System.out.println("That took " + (stop - start) + "ms.");

    } // main
} // class

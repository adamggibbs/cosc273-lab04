import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelPrimes {
	

    // the maximum prime number considered
    public static final int MAX = 1_000_000;
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

	// create a thread pool
	int nThreads = Runtime.getRuntime().availableProcessors();
	ExecutorService pool = Executors.newFixedThreadPool(nThreads);
	
	long start = System.currentTimeMillis();

	// Put your code here!

	int[] primes = Primes.getPrimesUpTo(MAX); // replace this!



	
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
    }
}

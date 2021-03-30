public class Primes {
    //public static final int ROOT_MAX = (int) Math.sqrt(Integer.MAX_VALUE);
    public static final int ROOT_MAX = 11_000;
    public static final int MAX = ROOT_MAX * ROOT_MAX;
    public static final int N_PRIMES = (int) (1.2 * MAX / Math.log(MAX));

    public static boolean[] isPrime = new boolean[MAX];
    public static int[] primes = new int[N_PRIMES];

    public static int countPrimes () {
    	int i = 0;
    	for (i = 0; i < MAX; ++i) {
    	    if (primes[i] == 0)
    		break;
    	}

    	return i;
    }

    

    public static void main (String[] args) {
	long start = System.nanoTime();

	// initialize isPrime[i] to true for i >= 2
	for (int i = 2; i < MAX; ++i) {
	    isPrime[i] = true;
	}

	int curIndex = 0;

	// use sieve of Eratosthenes to compute isPrime[i]
	// after calling this, isPrime[i] evaluates to true
	// if and only if i is prime
	for (int i = 0; i < MAX; ++i) {
	    if (isPrime[i]) {
		primes[curIndex] = i;
		++curIndex;

		if (i < ROOT_MAX) {
		    int j = i * i;

		    while (j < MAX) {
			isPrime[j] = false;
			j += i;
		    }
		}
	    }
	}

	// int index = 0;
	
	// for (int i = 0; i < MAX; ++i) {
	//     if (isPrime[i]) {
	// 	primes[index] = i;
	// 	++index;
	//     }
	// }

	long stop = System.nanoTime();

	System.out.println("Computed first " + countPrimes() + " primes in " + (stop - start) / 1_000_000 + " ms. The largest prime is " + primes[curIndex - 1] + ".");
	
    }
}

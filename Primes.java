/*
 * Primes.java: Compute an array of prime numbers up to a given (int)
 * value. The primary function is public static int[]
 * getPrimesUpTo(int max). This method returns an array of all primes
 * up to max (exclusive) in increasing order.
 */


public class Primes {

    private static final int MAX = 100_000_000;

    private static boolean[] getIsPrime (int max) {
	int rootMax = (int) Math.sqrt(max);
	boolean[] isPrime = new boolean[max];
	
	// initialize isPrime[i] to true for i >= 2
	for (int i = 2; i < max; ++i) {
	    isPrime[i] = true;
	}

	// use sieve of Eratosthenes to compute isPrime[i]
	// after calling this, isPrime[i] evaluates to true
	// if and only if i is prime
	for (int i = 0; i < max; ++i) {
	    if (isPrime[i]) {

		// mark multiples of i as composite
		if (i <= rootMax) {
		    
		    int j = i * i;

		    while (true) {
			
			isPrime[j] = false;
			
			if (j >= max - i) {
			    break;
			}
			
			j += i;
		    }
		} 
	    }
	}

	return isPrime;
    }

    // count the number of primes (i.e. true values) in isPrime
    private static int countPrimes (boolean[] isPrime) {
    	int count = 0;

	for (boolean b : isPrime) {
	    if (b) {
		++count;
	    }
	}

	return count;
    }

    /*
     * compute the primes up to max and return an array consisting of
     * those primes. The returned array is in sorted order.
     */
    public static int[] getPrimesUpTo(int max) {
	boolean[] isPrime = getIsPrime(max);
	int nPrimes = countPrimes(isPrime);

	int[] primes = new int[nPrimes];

	int count = 0;
	
	for (int i = 0; i < isPrime.length; ++i) {
	    if (isPrime[i]) {
		primes[count] = i;
		++count;
	    }
	}

	return primes;
    }

    
    public static void main (String[] args) {
	
	long start = System.nanoTime();

	int[] primes = getPrimesUpTo(MAX);

	long stop = System.nanoTime();

	System.out.println("Computed first " + primes.length + " primes in " + (stop - start) / 1_000_000 + " ms. The largest prime is " + primes[primes.length - 1] + ".");
	
    }
}

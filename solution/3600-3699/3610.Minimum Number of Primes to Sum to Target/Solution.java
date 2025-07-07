class Solution {
    static List<Integer> primes = new ArrayList<>();
    static {
        int x = 2;
        int M = 1000;
        while (primes.size() < M) {
            boolean is_prime = true;
            for (int p : primes) {
                if (p * p > x) {
                    break;
                }
                if (x % p == 0) {
                    is_prime = false;
                    break;
                }
            }
            if (is_prime) {
                primes.add(x);
            }
            x++;
        }
    }

    public int minNumberOfPrimes(int n, int m) {
        int[] f = new int[n + 1];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int x : primes.subList(0, m)) {
            for (int i = x; i <= n; i++) {
                f[i] = Math.min(f[i], f[i - x] + 1);
            }
        }
        return f[n] < inf ? f[n] : -1;
    }
}

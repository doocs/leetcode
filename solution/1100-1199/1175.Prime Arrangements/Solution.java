class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numPrimeArrangements(int n) {
        int cnt = count(n);
        long ans = f(cnt) * f(n - cnt);
        return (int) (ans % MOD);
    }

    private long f(int n) {
        long ans = 1;
        for (int i = 2; i <= n; ++i) {
            ans = (ans * i) % MOD;
        }
        return ans;
    }

    private int count(int n) {
        int cnt = 0;
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i <= n; ++i) {
            if (primes[i]) {
                ++cnt;
                for (int j = i + i; j <= n; j += i) {
                    primes[j] = false;
                }
            }
        }
        return cnt;
    }
}
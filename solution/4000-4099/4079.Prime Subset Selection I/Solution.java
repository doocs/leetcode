class Solution {
    static int mx = 1000000;
    static List<Integer> primes = new ArrayList<>();

    static {
        boolean[] isPrime = new boolean[mx + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= mx; i++) {
            if (isPrime[i]) {
                primes.add(i);
                if (i <= mx / i) {
                    for (int j = i * i; j <= mx; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
    }

    public List<Integer> maxPrimes(int n, int s) {
        List<Integer> ans = new ArrayList<>();
        for (int p : primes) {
            if (p > n || p > s) {
                break;
            }
            ans.add(p);
            s -= p;
        }
        return ans;
    }
}
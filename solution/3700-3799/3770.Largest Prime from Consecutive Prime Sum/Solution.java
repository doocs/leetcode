class Solution {
    private static final int MX = 500000;
    private static final boolean[] IS_PRIME = new boolean[MX + 1];
    private static final List<Integer> PRIMES = new ArrayList<>();
    private static final List<Integer> S = new ArrayList<>();

    static {
        Arrays.fill(IS_PRIME, true);
        IS_PRIME[0] = false;
        IS_PRIME[1] = false;

        for (int i = 2; i <= MX; i++) {
            if (IS_PRIME[i]) {
                PRIMES.add(i);
                if ((long) i * i <= MX) {
                    for (int j = i * i; j <= MX; j += i) {
                        IS_PRIME[j] = false;
                    }
                }
            }
        }

        S.add(0);
        int t = 0;
        for (int x : PRIMES) {
            t += x;
            if (t > MX) {
                break;
            }
            if (IS_PRIME[t]) {
                S.add(t);
            }
        }
    }

    public int largestPrime(int n) {
        int i = Collections.binarySearch(S, n + 1);
        if (i < 0) {
            i = ~i;
        }
        return S.get(i - 1);
    }
}

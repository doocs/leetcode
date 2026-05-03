class Solution {
    private static final int LIMIT = 1000;
    private static final boolean[] isPrime = new boolean[LIMIT + 1];
    static {
        for (int i = 0; i <= LIMIT; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= LIMIT; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= LIMIT; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    public int sumOfPrimesInRange(int n) {
        int r = Integer.parseInt(new StringBuilder(String.valueOf(n)).reverse().toString());
        int low = Math.min(n, r);
        int high = Math.max(n, r);
        int ans = 0;
        for (int x = low; x <= high; x++) {
            if (isPrime[x]) {
                ans += x;
            }
        }
        return ans;
    }
}

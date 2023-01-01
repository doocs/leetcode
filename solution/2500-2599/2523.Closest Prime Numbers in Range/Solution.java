class Solution {
    public int[] closestPrimes(int left, int right) {
        int cnt = 0;
        boolean[] st = new boolean[right + 1];
        int[] prime = new int[right + 1];
        for (int i = 2; i <= right; ++i) {
            if (!st[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; prime[j] <= right / i; ++j) {
                st[prime[j] * i] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        int i = -1, j = -1;
        for (int k = 0; k < cnt; ++k) {
            if (prime[k] >= left && prime[k] <= right) {
                if (i == -1) {
                    i = k;
                }
                j = k;
            }
        }
        int[] ans = new int[] {-1, -1};
        if (i == j || i == -1) {
            return ans;
        }
        int mi = 1 << 30;
        for (int k = i; k < j; ++k) {
            int d = prime[k + 1] - prime[k];
            if (d < mi) {
                mi = d;
                ans[0] = prime[k];
                ans[1] = prime[k + 1];
            }
        }
        return ans;
    }
}
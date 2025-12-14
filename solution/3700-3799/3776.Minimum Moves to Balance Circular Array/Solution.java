class Solution {
    public long minMoves(int[] balance) {
        long sum = 0;
        for (int b : balance) {
            sum += b;
        }
        if (sum < 0) {
            return -1;
        }

        int n = balance.length;
        int mn = balance[0];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (balance[i] < mn) {
                mn = balance[i];
                idx = i;
            }
        }

        if (mn >= 0) {
            return 0;
        }

        int need = -mn;
        long ans = 0;

        for (int j = 1; j < n; j++) {
            int a = balance[(idx - j + n) % n];
            int b = balance[(idx + j) % n];

            int c1 = Math.min(a, need);
            need -= c1;
            ans += (long) c1 * j;

            int c2 = Math.min(b, need);
            need -= c2;
            ans += (long) c2 * j;
        }

        return ans;
    }
}

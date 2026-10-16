class Solution {
    private static final long MOD = 1000000007L;
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;
    public int sumCounts(int[] nums) {
        int n = nums.length;
        int size = 4 * n;
        sum = new long[size];
        sumSq = new long[size];
        lazy = new long[size];
        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int p = last.getOrDefault(x, -1);
            update(1, 0, n - 1, p + 1, r, 1L);
            last.put(x, r);
            ans = (ans + sumSq[1]) % MOD;
        }
        return (int) ans;
    }
    private void apply(int node, int l, int r, long v) {
        long len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2L * v % MOD * sum[node] % MOD + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0 && l != r) {
            int mid = (l + r) >> 1;
            long v = lazy[node];
            apply(node << 1, l, mid, v);
            apply(node << 1 | 1, mid + 1, r, v);
            lazy[node] = 0;
        }
    }
    private void update(int node, int l, int r, int ql, int qr, long v) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) >> 1;
        update(node << 1, l, mid, ql, qr, v);
        update(node << 1 | 1, mid + 1, r, ql, qr, v);
        sum[node] = (sum[node << 1] + sum[node << 1 | 1]) % MOD;
        sumSq[node] = (sumSq[node << 1] + sumSq[node << 1 | 1]) % MOD;
    }
}
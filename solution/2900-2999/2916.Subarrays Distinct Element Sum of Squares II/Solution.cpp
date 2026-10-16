class Solution {
public:
    int sumCounts(vector<int>& nums) {
        const long long MOD = 1000000007LL;
        int n = nums.size();
        vector<long long> sum(4 * n, 0);     // sum of cnt
        vector<long long> sumSq(4 * n, 0);   // sum of cnt^2
        vector<long long> lazy(4 * n, 0);    // pending add
        function<void(int,int,int,long long)> apply = [&](int node, int l, int r, long long v) {
            long long len = r - l + 1;
            sumSq[node] = (sumSq[node] + 2LL * v % MOD * sum[node] % MOD + v * v % MOD * len % MOD) % MOD;
            sum[node] = (sum[node] + v * len) % MOD;
            lazy[node] = (lazy[node] + v) % MOD;
        };
        function<void(int,int,int)> pushDown = [&](int node, int l, int r) {
            if (lazy[node] != 0 && l != r) {
                int mid = (l + r) >> 1;
                long long v = lazy[node];
                apply(node << 1, l, mid, v);
                apply(node << 1 | 1, mid + 1, r, v);
                lazy[node] = 0;
            }
        };
        function<void(int,int,int,int,int,long long)> update = [&](int node, int l, int r, int ql, int qr, long long v) {
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
        };
        unordered_map<int, int> last;
        long long ans = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int p = last.count(x) ? last[x] : -1;
            update(1, 0, n - 1, p + 1, r, 1LL);
            last[x] = r;
            ans = (ans + sumSq[1]) % MOD;
        }
        return (int)ans;
    }
};
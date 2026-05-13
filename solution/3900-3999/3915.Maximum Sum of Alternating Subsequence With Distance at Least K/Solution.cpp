class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums, int K) {
        int n = nums.size();

        // 离散化
        int idx[n];
        map<int, int> mp;
        for (int x : nums) mp[x] = 1;
        int m = 0;
        for (auto& p : mp) p.second = ++m;
        for (int i = 0; i < n; i++) idx[i] = mp[nums[i]];

        const long long INF = 1e18;
        // tree[0]：前缀最大值（用于查询 < nums[i] 的最大 f[j][0]）
        // tree[1]：后缀最大值（用于查询 > nums[i] 的最大 f[j][1]）
        long long tree[2][m + 1];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j <= m; j++) tree[i][j] = -INF;

        // 树状数组模板开始

        auto lb = [&](int x) { return x & (-x); };

        auto update = [&](long long* tree, int pos, long long val) {
            for (; pos <= m; pos += lb(pos)) tree[pos] = max(tree[pos], val);
        };

        auto query = [&](long long* tree, int pos) {
            long long ret = -INF;
            for (; pos; pos -= lb(pos)) ret = max(ret, tree[pos]);
            return ret;
        };

        // 树状数组模板结束

        long long ans = 0;
        long long f[n + 1][2];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j < 2; j++) f[i][j] = -INF;
        // 滑动窗口：只有 j <= i - K 的位置才加入树状数组
        for (int i = 1, j = 1; i <= n; i++) {
            while (i - j >= K) {
                update(tree[0], idx[j - 1], f[j][0]);
                update(tree[1], m + 1 - idx[j - 1], f[j][1]);
                j++;
            }
            // 谷：从 tree[1] 查询值 > nums[i] 的最大 f[j][1]
            f[i][0] = max(0LL, query(tree[1], m - idx[i - 1])) + nums[i - 1];
            // 峰：从 tree[0] 查询值 < nums[i] 的最大 f[j][0]
            f[i][1] = max(0LL, query(tree[0], idx[i - 1] - 1)) + nums[i - 1];
            ans = max({ans, f[i][0], f[i][1]});
        }
        return ans;
    }
};

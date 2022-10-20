class Solution {
public:
    int numberOfNodes(int n, vector<int>& queries) {
        vector<int> tree(n + 1);
        vector<int> cnt(n + 1);
        for (int v : queries) ++cnt[v];
        function<void(int)> dfs = [&](int i) {
            if (i > n) return;
            tree[i] ^= 1;
            dfs(i << 1);
            dfs(i << 1 | 1);
        };
        for (int i = 0; i < n + 1; ++i) {
            if (cnt[i] & 1) {
                dfs(i);
            }
        }
        return accumulate(tree.begin(), tree.end(), 0);
    }
};
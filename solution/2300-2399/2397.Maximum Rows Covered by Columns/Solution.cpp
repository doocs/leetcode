class Solution {
public:
    int maximumRows(vector<vector<int>>& mat, int cols) {
        int m = mat.size(), n = mat[0].size();
        vector<int> arr(m);
        for (int i = 0; i < m; ++i) {
            int x = 0;
            for (int j = 0; j < n; ++j) x |= mat[i][j] << j;
            arr[i] = x;
        }
        int ans = 0;
        for (int mask = 1; mask <= 1 << n; ++mask) {
            if (__builtin_popcount(mask) > cols) continue;
            int t = 0;
            for (int v : arr) t += (v & mask) == v;
            ans = max(ans, t);
        }
        return ans;
    }
};
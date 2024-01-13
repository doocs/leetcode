class Solution {
public:
    int maxSumSubmatrix(vector<vector<int>>& matrix, int k) {
        int m = matrix.size(), n = matrix[0].size();
        const int inf = 1 << 30;
        int ans = -inf;
        for (int i = 0; i < m; ++i) {
            vector<int> nums(n);
            for (int j = i; j < m; ++j) {
                for (int h = 0; h < n; ++h) {
                    nums[h] += matrix[j][h];
                }
                set<int> ts;
                int s = 0;
                ts.insert(0);
                for (int x : nums) {
                    s += x;
                    auto it = ts.lower_bound(s - k);
                    if (it != ts.end()) {
                        ans = max(ans, s - *it);
                    }
                    ts.insert(s);
                }
            }
        }
        return ans;
    }
};
class Solution {
public:
    int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        vector<int> ans(n + 1, 0);
        ans[1] = 1;
        for (int i = 2; i < n + 1; ++i)
            ans[i] = i % 2 == 0 ? ans[i >> 1] : ans[i >> 1] + ans[(i >> 1) + 1];
        return *max_element(ans.begin(), ans.end());
    }
};
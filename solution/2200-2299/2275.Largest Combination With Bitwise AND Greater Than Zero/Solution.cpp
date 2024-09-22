class Solution {
public:
    int largestCombination(vector<int>& candidates) {
        int mx = *max_element(candidates.begin(), candidates.end());
        int m = 32 - __builtin_clz(mx);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int cnt = 0;
            for (int x : candidates) {
                cnt += x >> i & 1;
            }
            ans = max(ans, cnt);
        }
        return ans;
    }
};

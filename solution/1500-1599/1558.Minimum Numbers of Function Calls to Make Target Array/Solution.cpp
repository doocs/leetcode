class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = max(mx, v);
            ans += __builtin_popcount(v);
        }
        if (mx) ans += 31 - __builtin_clz(mx);
        return ans;
    }
};
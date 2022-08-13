class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        long long ans = 0;
        int cnt = 0;
        for (int v : nums) {
            if (v == 0)
                ++cnt;
            else {
                ans += 1ll * (1 + cnt) * cnt / 2;
                cnt = 0;
            }
        }
        ans += 1ll * (1 + cnt) * cnt / 2;
        return ans;
    }
};
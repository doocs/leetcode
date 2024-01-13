class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 0;
        int pre = 0, cnt = 0;
        for (int x : nums) {
            if (pre < x) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
            pre = x;
        }
        return ans;
    }
};
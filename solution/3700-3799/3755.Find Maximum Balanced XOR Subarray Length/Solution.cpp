class Solution {
public:
    int maxBalancedSubarray(vector<int>& nums) {
        unordered_map<long long, int> d;
        int ans = 0;
        int a = 0, b = nums.size();
        d[(long long) b] = -1;
        for (int i = 0; i < nums.size(); ++i) {
            a ^= nums[i];
            b += nums[i] % 2 == 0 ? 1 : -1;
            long long key = (1LL * a << 32) | b;
            if (d.contains(key)) {
                ans = max(ans, i - d[key]);
            } else {
                d[key] = i;
            }
        }
        return ans;
    }
};

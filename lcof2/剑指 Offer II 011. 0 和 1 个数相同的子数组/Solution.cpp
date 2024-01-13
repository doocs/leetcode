class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> d;
        d[0] = -1;
        int ans = 0, s = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            s += nums[i] ? 1 : -1;
            if (d.count(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
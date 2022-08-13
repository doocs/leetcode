class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        x = -x;
        for (int& v : nums) x += v;
        int s = 0, n = nums.size();
        unordered_map<int, int> seen;
        seen[0] = -1;
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (!seen.count(s)) seen[s] = i;
            if (seen.count(s - x)) {
                int j = seen[s - x];
                ans = min(ans, n - (i - j));
            }
        }
        return ans == INT_MAX ? -1 : ans;
    }
};
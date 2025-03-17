class Solution {
public:
    int maxSum(vector<int>& nums) {
        int mx = ranges::max(nums);
        if (mx <= 0) {
            return mx;
        }
        unordered_set<int> s;
        int ans = 0;
        for (int x : nums) {
            if (x < 0 || s.contains(x)) {
                continue;
            }
            ans += x;
            s.insert(x);
        }
        return ans;
    }
};

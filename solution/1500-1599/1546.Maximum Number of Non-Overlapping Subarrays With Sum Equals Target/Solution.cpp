class Solution {
public:
    int maxNonOverlapping(vector<int>& nums, int target) {
        int ans = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            unordered_set<int> vis{{0}};
            int s = 0;
            while (i < n) {
                s += nums[i];
                if (vis.count(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                vis.insert(s);
            }
        }
        return ans;
    }
};
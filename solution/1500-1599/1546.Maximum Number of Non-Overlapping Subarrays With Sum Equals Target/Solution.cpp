class Solution {
public:
    int maxNonOverlapping(vector<int>& nums, int target) {
        int i = 0, n = nums.size();
        int ans = 0;
        while (i < n) {
            int s = 0;
            unordered_set<int> seen;
            seen.insert(0);
            while (i < n) {
                s += nums[i];
                if (seen.count(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                seen.insert(s);
            }
            ++i;
        }
        return ans;
    }
};
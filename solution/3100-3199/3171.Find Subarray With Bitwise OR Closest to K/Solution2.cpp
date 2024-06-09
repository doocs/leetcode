class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        int ans = abs(nums[0] - k);
        unordered_set<int> pre;
        pre.insert(nums[0]);
        for (int x : nums) {
            unordered_set<int> cur;
            cur.insert(x);
            for (int y : pre) {
                cur.insert(x & y);
            }
            for (int y : cur) {
                ans = min(ans, abs(y - k));
            }
            pre = move(cur);
        }
        return ans;
    }
};
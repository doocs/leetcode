class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        int s1 = robRange(nums, 0, n - 2);
        int s2 = robRange(nums, 1, n - 1);
        return max(s1, s2);
    }
private:
    int robRange(vector<int>& nums, int start, int end) {
        if (end - start == 0) return nums[start];
        int pre = 0;
        int cur = nums[start];
        for (int i = start + 1; i < end + 1; ++i) {
            int t = max(pre + nums[i], cur);
            pre = cur;
            cur = t;
        }
        return cur;
    }
};
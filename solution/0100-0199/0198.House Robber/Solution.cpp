class Solution {
public:
    int rob(vector<int>& nums) {
        int n;
        if ((n = nums.size()) == 0) return 0;
        return robRange(nums, 0, n - 1);
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
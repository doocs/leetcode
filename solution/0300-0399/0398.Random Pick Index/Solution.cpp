class Solution {
public:
    vector<int> nums;

    Solution(vector<int>& nums) {
        this->nums = nums;
    }

    int pick(int target) {
        int n = 0, ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == target) {
                ++n;
                int x = 1 + rand() % n;
                if (n == x) ans = i;
            }
        }
        return ans;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * int param_1 = obj->pick(target);
 */
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int n = nums.size();
        vector<bool> isAdded(n);
        int count = 0;
        for (int i = n - 1;; --i) {
            if (nums[i] > k || isAdded[nums[i] - 1]) {
                continue;
            }
            isAdded[nums[i] - 1] = true;
            if (++count == k) {
                return n - i;
            }
        }
    }
};
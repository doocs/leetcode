class Solution {
public:
    vector<int> reverseSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                swap(nums[l++], nums[r--]);
            }
        }
        return nums;
    }
};

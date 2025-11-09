class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, 1), right(n, 1);

        for (int i = 1; i < n; ++i) {
            if (nums[i] >= nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int ans = ranges::max(left);

        for (int i = 0; i < n; ++i) {
            int a = (i - 1 < 0) ? 0 : left[i - 1];
            int b = (i + 1 >= n) ? 0 : right[i + 1];
            if (i - 1 >= 0 && i + 1 < n && nums[i - 1] > nums[i + 1]) {
                ans = max({ans, a + 1, b + 1});
            } else {
                ans = max(ans, a + b + 1);
            }
        }

        return ans;
    }
};

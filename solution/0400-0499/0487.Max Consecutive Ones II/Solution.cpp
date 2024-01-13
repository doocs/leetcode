class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n), right(n);
        for (int i = 0; i < n; ++i) {
            if (nums[i]) {
                left[i] = i == 0 ? 1 : left[i - 1] + 1;
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (nums[i]) {
                right[i] = i == n - 1 ? 1 : right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int t = 0;
            if (i) {
                t += left[i - 1];
            }
            if (i < n - 1) {
                t += right[i + 1];
            }
            ans = max(ans, t + 1);
        }
        return ans;
    }
};
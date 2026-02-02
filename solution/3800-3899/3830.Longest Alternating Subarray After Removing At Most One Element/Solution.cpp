class Solution {
public:
    int longestAlternating(vector<int>& nums) {
        int n = nums.size();
        vector<int> l1(n, 1), l2(n, 1), r1(n, 1), r2(n, 1);

        int ans = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                l1[i] = l2[i - 1] + 1;
            } else if (nums[i - 1] > nums[i]) {
                l2[i] = l1[i - 1] + 1;
            }
            ans = max(ans, l1[i]);
            ans = max(ans, l2[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                r1[i] = r2[i + 1] + 1;
            } else if (nums[i + 1] < nums[i]) {
                r2[i] = r1[i + 1] + 1;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] < nums[i + 1]) {
                ans = max(ans, l2[i - 1] + r2[i + 1]);
            } else if (nums[i - 1] > nums[i + 1]) {
                ans = max(ans, l1[i - 1] + r1[i + 1]);
            }
        }

        return ans;
    }
};

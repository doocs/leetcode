class Solution {
public:
    vector<int> findValidElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n);
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = max(right[i + 1], nums[i]);
        }
        int left = 0;
        vector<int> ans;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x > left || i == n - 1 || x > right[i + 1]) {
                ans.push_back(x);
            }
            left = max(left, x);
        }
        return ans;
    }
};

class Solution {
public:
    int maxSelectedElements(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 1, prev = INT_MIN;
        int f0 = 1, f1 = 1;
        for (int x : nums) {
            if (x == prev) {
                f1 = f0 + 1;
            } else if (x == prev + 1) {
                ++f0;
                ++f1;
            } else if (x == prev + 2) {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = max({ans, f0, f1});
            prev = x;
        }
        return ans;
    }
};

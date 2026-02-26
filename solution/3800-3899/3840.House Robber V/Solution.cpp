class Solution {
public:
    long long rob(vector<int>& nums, vector<int>& colors) {
        int n = nums.size();
        long long f = 0, g = nums[0];
        for (int i = 1; i < n; i++) {
            if (colors[i - 1] == colors[i]) {
                long long gg = f + nums[i];
                f = max(f, g);
                g = gg;
            } else {
                long long gg = max(f, g) + nums[i];
                f = max(f, g);
                g = gg;
            }
        }
        return max(f, g);
    }
};

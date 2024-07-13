class Solution {
public:
    int findMinDifference(vector<string>& timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int n = timePoints.size();
        vector<int> nums(n + 1);
        for (int i = 0; i < n; ++i) {
            int hours = stoi(timePoints[i].substr(0, 2));
            int minutes = stoi(timePoints[i].substr(3, 2));
            nums[i] = hours * 60 + minutes;
        }
        sort(nums.begin(), nums.begin() + n);
        nums[n] = nums[0] + 1440;
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) {
            ans = min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
};
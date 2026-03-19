class Solution {
public:
    long long validSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> peaks;

        for (int i = 1; i < n - 1; ++i) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks.push_back(i);
            }
        }

        long long ans = 0;
        for (int j = 0; j < peaks.size(); ++j) {
            int p = peaks[j];

            int leftMin = max(p - k, 0);
            if (j > 0) {
                leftMin = max(leftMin, peaks[j - 1] + 1);
            }

            int rightMax = min(p + k, n - 1);
            if (j < peaks.size() - 1) {
                rightMax = min(rightMax, peaks[j + 1] - 1);
            }

            ans += 1LL * (p - leftMin + 1) * (rightMax - p + 1);
        }

        return ans;
    }
};

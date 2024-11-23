class Solution {
public:
    int minimizeMaxDifference(vector<int>& nums) {
        int left = 0, right = 1e9, result = 1e9;

        auto isValid = [&](int maxDiff) {
            int n = nums.size();
            int prev = nums[0];
            for (int i = 1; i < n; ++i) {
                if (nums[i] == -1) {
                    continue;
                }
                if (prev != -1 && abs(nums[i] - prev) > maxDiff) {
                    return false;
                }
                prev = nums[i];
            }
            return true;
        };

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
};

class Solution {
public:
    int minimizeMaxDifference(vector<int>& nums) {
        int n = nums.size();
        int minVal = INT_MAX, maxVal = INT_MIN;

        for (int i = 0; i < n; ++i) {
            if (nums[i] != -1) {
                minVal = min(minVal, nums[i]);
                maxVal = max(maxVal, nums[i]);
            }
        }

        if (minVal == INT_MAX) {
            return 0;
        }
 
        auto isPossible = [&](int maxDiff) -> bool {
            int prev = -1;

            for (int i = 0; i < n; ++i) {
                if (nums[i] != -1) {
                    prev = nums[i];
                } else {
                    
                    if (prev != -1) {
                        if (abs(prev - minVal) > maxDiff && abs(prev - maxVal) > maxDiff) {
                            return false;
                        }
                        prev = (abs(prev - minVal) <= abs(prev - maxVal)) ? minVal : maxVal;
                    } else {
                        prev = minVal; 
                    }
                }
            }

            return true;
        };

        int left = 0, right = maxVal - minVal, result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isPossible(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
};

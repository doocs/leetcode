class Solution {
public:
    int countMajoritySubarrays(vector<int>& nums, int target) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] == target;
                if (cnt * 2 > j - i + 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
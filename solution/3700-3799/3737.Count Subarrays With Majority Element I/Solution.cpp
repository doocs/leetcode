class Solution {
public:
    int countMajoritySubarrays(vector<int>& nums, int target) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            unordered_map<int, int> cnt;
            for (int j = i; j < n; ++j) {
                int k = j - i + 1;
                cnt[nums[j]]++;
                if (cnt[target] > k / 2) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};

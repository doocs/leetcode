class Solution {
public:
    int countCompleteSubarrays(vector<int>& nums) {
        unordered_map<int, int> d;
        for (int x : nums) {
            d[x] = 1;
        }
        int cnt = d.size();
        d.clear();
        int ans = 0, n = nums.size();
        for (int i = 0, j = 0; j < n; ++j) {
            d[nums[j]]++;
            while (d.size() == cnt) {
                ans += n - j;
                if (--d[nums[i]] == 0) {
                    d.erase(nums[i]);
                }
                ++i;
            }
        }
        return ans;
    }
};
class Solution {
public:
    int countValidSubarrays(vector<int>& nums, int x) {
        int n = nums.size();
        int ans = 0;

        for (int l = 0; l < n; ++l) {
            long long s = 0;
            for (int r = l; r < n; ++r) {
                s += nums[r];
                if (s % 10 == x && to_string(s)[0] - '0' == x) {
                    ++ans;
                }
            }
        }

        return ans;
    }
};
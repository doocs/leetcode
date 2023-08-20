class Solution {
public:
    int longestEqualSubarray(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int mx = 0, l = 0;
        for (int r = 0; r < nums.size(); ++r) {
            mx = max(mx, ++cnt[nums[r]]);
            if (r - l + 1 - mx > k) {
                --cnt[nums[l++]];
            }
        }
        return mx;
    }
};
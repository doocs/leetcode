class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        vector<int> ans(2);
        for (int x = 1; x <= n; ++x) {
            if (cnt[x] == 2) {
                ans[0] = x;
            } else if (cnt[x] == 0) {
                ans[1] = x;
            }
        }
        return ans;
    }
};
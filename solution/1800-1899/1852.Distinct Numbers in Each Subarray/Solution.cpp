class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int i = 0; i < k; ++i) {
            ++cnt[nums[i]];
        }
        int n = nums.size();
        vector<int> ans;
        ans.push_back(cnt.size());
        for (int i = k; i < n; ++i) {
            ++cnt[nums[i]];
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            ans.push_back(cnt.size());
        }
        return ans;
    }
};
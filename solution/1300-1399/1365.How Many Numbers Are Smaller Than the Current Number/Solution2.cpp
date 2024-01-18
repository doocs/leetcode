class Solution {
public:
    vector<int> smallerNumbersThanCurrent(vector<int>& nums) {
        int cnt[102]{};
        for (int& x : nums) {
            ++cnt[x + 1];
        }
        for (int i = 1; i < 102; ++i) {
            cnt[i] += cnt[i - 1];
        }
        vector<int> ans;
        for (int& x : nums) {
            ans.push_back(cnt[x]);
        }
        return ans;
    }
};
class Solution {
public:
    vector<int> findLonely(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        vector<int> ans;
        for (auto& [x, v] : cnt) {
            if (v == 1 && !cnt.contains(x - 1) && !cnt.contains(x + 1)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
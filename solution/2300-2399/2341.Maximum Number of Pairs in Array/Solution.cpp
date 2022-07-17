class Solution {
public:
    vector<int> numberOfPairs(vector<int>& nums) {
        vector<int> cnt(101);
        for (int v : nums) ++cnt[v];
        int s = 0;
        for (int v : cnt) s += v / 2;
        vector<int> ans(2);
        ans[0] = s;
        ans[1] = nums.size() - s * 2;
        return ans;
    }
};
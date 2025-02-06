class Solution {
public:
    vector<int> longestCommonSubsequence(vector<vector<int>>& arrays) {
        int cnt[101]{};
        for (const auto& row : arrays) {
            for (int x : row) {
                ++cnt[x];
            }
        }
        vector<int> ans;
        for (int i = 0; i < 101; ++i) {
            if (cnt[i] == arrays.size()) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};

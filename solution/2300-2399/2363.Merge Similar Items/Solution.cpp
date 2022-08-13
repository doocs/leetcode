class Solution {
public:
    vector<vector<int>> mergeSimilarItems(vector<vector<int>>& items1, vector<vector<int>>& items2) {
        vector<int> cnt(1010);
        for (auto& e : items1) cnt[e[0]] += e[1];
        for (auto& e : items2) cnt[e[0]] += e[1];
        vector<vector<int>> ans;
        for (int i = 0; i < cnt.size(); ++i)
            if (cnt[i]) ans.push_back({i, cnt[i]});
        return ans;
    }
};
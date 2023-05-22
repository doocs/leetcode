class Solution {
public:
    vector<vector<int>> highFive(vector<vector<int>>& items) {
        vector<int> d[1001];
        for (auto& item : items) {
            int i = item[0], x = item[1];
            d[i].push_back(x);
        }
        vector<vector<int>> ans;
        for (int i = 1; i <= 1000; ++i) {
            if (!d[i].empty()) {
                sort(d[i].begin(), d[i].end(), greater<int>());
                int s = 0;
                for (int j = 0; j < 5; ++j) {
                    s += d[i][j];
                }
                ans.push_back({i, s / 5});
            }
        }
        return ans;
    }
};
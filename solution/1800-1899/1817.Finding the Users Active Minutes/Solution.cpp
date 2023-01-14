class Solution {
public:
    vector<int> findingUsersActiveMinutes(vector<vector<int>>& logs, int k) {
        unordered_map<int, unordered_set<int>> d;
        for (auto& log : logs) {
            int i = log[0], t = log[1];
            d[i].insert(t);
        }
        vector<int> ans(k);
        for (auto& [_, ts] : d) {
            ++ans[ts.size() - 1];
        }
        return ans;
    }
};
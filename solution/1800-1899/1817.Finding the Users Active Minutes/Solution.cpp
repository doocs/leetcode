class Solution {
public:
    vector<int> findingUsersActiveMinutes(vector<vector<int>>& logs, int k) {
        unordered_map<int, unordered_set<int>> d;
        for (auto& e : logs) d[e[0]].insert(e[1]);
        vector<int> ans(k);
        for (auto& e : d) ++ans[e.second.size() - 1];
        return ans;
    }
};
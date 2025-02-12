class Solution {
public:
    int maxStudentsOnBench(vector<vector<int>>& students) {
        unordered_map<int, unordered_set<int>> d;
        for (const auto& e : students) {
            d[e[0]].insert(e[1]);
        }
        int ans = 0;
        for (auto& [_, s] : d) {
            ans = max(ans, (int) s.size());
        }
        return ans;
    }
};

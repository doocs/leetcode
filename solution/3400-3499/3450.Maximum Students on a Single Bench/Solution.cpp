class Solution {
public:
    int maxStudentsOnBench(vector<vector<int>>& students) {
        unordered_map<int, unordered_set<int>> d;
        for (const auto& e : students) {
            int studentId = e[0], benchId = e[1];
            d[benchId].insert(studentId);
        }
        int ans = 0;
        for (const auto& s : d) {
            ans = max(ans, (int) s.second.size());
        }
        return ans;
    }
};

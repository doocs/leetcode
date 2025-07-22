class Solution {
public:
    vector<vector<int>> sortTheStudents(vector<vector<int>>& score, int k) {
        ranges::sort(score, [k](const auto& a, const auto& b) { return a[k] > b[k]; });
        return score;
    }
};

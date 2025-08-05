class Solution {
public:
    vector<vector<int>> sortThreats(vector<vector<int>>& threats) {
        sort(threats.begin(), threats.end(), [](const vector<int>& a, const vector<int>& b) {
            long long score1 = 2LL * a[1] + a[2];
            long long score2 = 2LL * b[1] + b[2];
            if (score1 == score2) {
                return a[0] < b[0];
            }
            return score2 < score1;
        });
        return threats;
    }
};

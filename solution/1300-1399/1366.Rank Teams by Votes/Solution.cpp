class Solution {
public:
    string rankTeams(vector<string>& votes) {
        int n = votes[0].size();
        int cnt[26][n];
        memset(cnt, 0, sizeof cnt);
        for (auto& vote : votes) {
            for (int i = 0; i < n; ++i) {
                cnt[vote[i] - 'A'][i]++;
            }
        }
        string ans = votes[0];
        sort(ans.begin(), ans.end(), [&](auto& a, auto& b) {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < n; ++k) {
                if (cnt[i][k] != cnt[j][k]) {
                    return cnt[i][k] > cnt[j][k];
                }
            }
            return a < b;
        });
        return ans;
    }
};
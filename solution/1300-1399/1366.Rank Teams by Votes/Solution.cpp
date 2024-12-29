class Solution {
public:
    string rankTeams(vector<string>& votes) {
        int m = votes[0].size();
        array<array<int, 27>, 26> cnt{};

        for (const auto& vote : votes) {
            for (int i = 0; i < m; ++i) {
                ++cnt[vote[i] - 'A'][i];
            }
        }
        string s = votes[0];
        ranges::sort(s, [&](char a, char b) {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < m; ++k) {
                if (cnt[i][k] != cnt[j][k]) {
                    return cnt[i][k] > cnt[j][k];
                }
            }
            return a < b;
        });
        return string(s.begin(), s.end());
    }
};

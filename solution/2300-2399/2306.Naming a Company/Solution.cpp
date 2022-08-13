class Solution {
public:
    long long distinctNames(vector<string>& ideas) {
        unordered_set<string> s(ideas.begin(), ideas.end());
        vector<vector<int>> f(26, vector<int>(26));
        for (auto v : ideas) {
            int i = v[0] - 'a';
            for (int j = 0; j < 26; ++j) {
                v[0] = j + 'a';
                if (!s.count(v)) {
                    ++f[i][j];
                }
            }
        }
        long long ans = 0;
        for (auto v : ideas) {
            int i = v[0] - 'a';
            for (int j = 0; j < 26; ++j) {
                v[0] = j + 'a';
                if (!s.count(v)) {
                    ans += f[j][i];
                }
            }
        }
        return ans;
    }
};
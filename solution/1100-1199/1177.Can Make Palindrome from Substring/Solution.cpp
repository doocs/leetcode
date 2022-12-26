class Solution {
public:
    vector<bool> canMakePaliQueries(string s, vector<vector<int>>& queries) {
        int n = s.size();
        int cnt[n + 1][26];
        memset(cnt, 0, sizeof cnt);
        for (int i = 1; i <= n; ++i) {
            int j = s[i - 1] - 'a';
            for (int k = 0; k < 26; ++k) {
                cnt[i][k] = cnt[i - 1][k];
            }
            cnt[i][j]++;
        }
        vector<bool> ans;
        for (auto& q : queries) {
            int left = q[0], right = q[1], k = q[2];
            int x = 0;
            for (int j = 0; j < 26; ++j) {
                x += (cnt[right + 1][j] - cnt[left][j]) & 1;
            }
            ans.emplace_back(x / 2 <= k);
        }
        return ans;
    }
};
class Solution {
public:
    int numTilePossibilities(string tiles) {
        vector<int> cnt(26);
        for (char& c : tiles) ++cnt[c - 'A'];
        return dfs(cnt);
    }

    int dfs(vector<int>& cnt) {
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) {
                --cnt[i];
                ++res;
                res += dfs(cnt);
                ++cnt[i];
            }
        }
        return res;
    }
};
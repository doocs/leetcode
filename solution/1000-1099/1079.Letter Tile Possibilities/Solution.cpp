class Solution {
public:
    int numTilePossibilities(string tiles) {
        int cnt[26]{};
        for (char c : tiles) {
            ++cnt[c - 'A'];
        }
        function<int(int* cnt)> dfs = [&](int* cnt) -> int {
            int res = 0;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0) {
                    ++res;
                    --cnt[i];
                    res += dfs(cnt);
                    ++cnt[i];
                }
            }
            return res;
        };
        return dfs(cnt);
    }
};
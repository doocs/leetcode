class Solution {
public:
    int longestSubstring(string s, int k) {
        function<int(int, int)> dfs = [&](int l, int r) -> int {
            int cnt[26] = {0};
            for (int i = l; i <= r; ++i) {
                cnt[s[i] - 'a']++;
            }
            char split = 0;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0 && cnt[i] < k) {
                    split = 'a' + i;
                    break;
                }
            }
            if (split == 0) {
                return r - l + 1;
            }
            int i = l;
            int ans = 0;
            while (i <= r) {
                while (i <= r && s[i] == split) {
                    ++i;
                }
                if (i >= r) {
                    break;
                }
                int j = i;
                while (j <= r && s[j] != split) {
                    ++j;
                }
                int t = dfs(i, j - 1);
                ans = max(ans, t);
                i = j;
            }
            return ans;
        };
        return dfs(0, s.size() - 1);
    }
};
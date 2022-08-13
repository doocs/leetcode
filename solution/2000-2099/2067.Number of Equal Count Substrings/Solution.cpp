class Solution {
public:
    int equalCountSubstrings(string s, int count) {
        int n = s.size();
        if (count > n) return 0;
        vector<vector<int>> counter(n + 1, vector<int>(26));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int idx = s[i] - 'a';
            for (int j = 0; j < 26; ++j) counter[i + 1][j] = counter[i][j];
            counter[i + 1][idx] = counter[i][idx] + 1;
            int l = 0;
            for (int k = 0; k < 26; ++k) {
                l += count;
                int j = i - l + 1;
                if (j < 0) break;
                ans += check(j, i, count, counter);
            }
        }
        return ans;
    }

    bool check(int i, int j, int count, vector<vector<int>>& counter) {
        auto& c1 = counter[i];
        auto& c2 = counter[j + 1];
        for (int k = 0; k < 26; ++k) {
            if (c2[k] == 0 || c1[k] == c2[k]) continue;
            if (c2[k] - c1[k] != count) return false;
        }
        return true;
    }
};
class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        int m = s.size(), n = p.size();
        vector<int> ans;
        if (m < n) {
            return ans;
        }
        vector<int> cnt(26);
        for (int i = 0; i < n; ++i) {
            ++cnt[s[i] - 'a'];
            --cnt[p[i] - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            ans.push_back(0);
        }
        for (int i = n; i < m; ++i) {
            int a = s[i - n] - 'a';
            int b = s[i] - 'a';
            if (cnt[a] == 0) {
                ++diff;
            }
            --cnt[a];
            if (cnt[a] == 0) {
                --diff;
            }
            if (cnt[b] == 0) {
                ++diff;
            }
            ++cnt[b];
            if (cnt[b] == 0) {
                --diff;
            }
            if (diff == 0) {
                ans.push_back(i - n + 1);
            }
        }
        return ans;
    }
};
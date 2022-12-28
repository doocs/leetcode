class Solution {
public:
    int maxRepOpt1(string text) {
        int cnt[26] = {0};
        for (char& c : text) {
            ++cnt[c - 'a'];
        }
        int n = text.size();
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text[j] == text[i]) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text[k] == text[i]) {
                ++k;
            }
            int r = k - j - 1;
            ans = max(ans, min(l + r + 1, cnt[text[i] - 'a']));
            i = j;
        }
        return ans;
    }
};
class Solution {
public:
    string smallestPalindrome(string s) {
        vector<int> cnt(26);
        for (char c : s) {
            cnt[c - 'a']++;
        }
        string t = "";
        string ch = "";
        for (char c = 'a'; c <= 'z'; ++c) {
            int v = cnt[c - 'a'] / 2;
            if (v > 0) {
                t.append(v, c);
            }
            cnt[c - 'a'] -= v * 2;
            if (cnt[c - 'a'] == 1) {
                ch = string(1, c);
            }
        }
        string ans = t;
        ans += ch;
        string rev = t;
        reverse(rev.begin(), rev.end());
        ans += rev;
        return ans;
    }
};

class Solution {
public:
    string largestPalindromic(string num) {
        vector<int> cnt(10);
        for (char c : num) ++cnt[c - '0'];
        string mid = "";
        for (int i = 9; ~i; --i) {
            if (cnt[i] % 2) {
                mid += (i + '0');
                --cnt[i];
                break;
            }
        }
        string t = "";
        for (int i = 0; i < 10; ++i) {
            if (cnt[i]) {
                cnt[i] >>= 1;
                while (cnt[i]--) {
                    t += (i + '0');
                }
            }
        }
        while (t.size() && t.back() == '0') {
            t.pop_back();
        }
        string ans = t;
        reverse(ans.begin(), ans.end());
        ans += mid + t;
        return ans == "" ? "0" : ans;
    }
};
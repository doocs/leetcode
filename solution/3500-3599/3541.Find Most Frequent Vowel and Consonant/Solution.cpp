class Solution {
public:
    int maxFreqSum(string s) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 0;
        for (int i = 0; i < 26; ++i) {
            char c = 'a' + i;
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                a = max(a, cnt[i]);
            } else {
                b = max(b, cnt[i]);
            }
        }
        return a + b;
    }
};
class Solution {
public:
    bool canConstruct(string s, int k) {
        if (s.size() < k) {
            return false;
        }
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            x += v & 1;
        }
        return x <= k;
    }
};
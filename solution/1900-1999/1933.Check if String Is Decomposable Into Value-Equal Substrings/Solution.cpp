class Solution {
public:
    bool isDecomposable(string s) {
        int i = 0, n = s.size();
        int cnt2 = 0;
        while (i < n) {
            int j = i;
            while (j < n && s[j] == s[i]) ++j;
            if ((j - i) % 3 == 1) return false;
            if ((j - i) % 3 == 2 && ++cnt2 > 1) return false;
            i = j;
        }
        return cnt2 == 1;
    }
};
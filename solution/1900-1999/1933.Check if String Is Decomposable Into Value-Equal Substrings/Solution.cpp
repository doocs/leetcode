class Solution {
public:
    bool isDecomposable(string s) {
        int cnt2 = 0;
        for (int i = 0, n = s.size(); i < n;) {
            int j = i;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            if ((j - i) % 3 == 1) {
                return false;
            }
            cnt2 += (j - i) % 3 == 2;
            if (cnt2 > 1) {
                return false;
            }
            i = j;
        }
        return cnt2 == 1;
    }
};
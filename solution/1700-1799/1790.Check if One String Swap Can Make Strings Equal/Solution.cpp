class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        char c1 = 0, c2 = 0;
        int n = s1.size();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s1[i] != s2[i]) {
                ++cnt;
                if ((cnt == 2 && (c1 != s2[i] || c2 != s1[i])) || cnt > 2) return false;
                c1 = s1[i];
                c2 = s2[i];
            }
        }
        return cnt == 0 || cnt == 2;
    }
};
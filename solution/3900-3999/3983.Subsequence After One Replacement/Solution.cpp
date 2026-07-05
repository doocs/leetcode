class Solution {
public:
    bool canMakeSubsequence(string s, string t) {
        int m = s.size(), n = t.size();
        int i0 = 0, i1 = 0, j = 0;

        while (i1 < m && j < n) {
            if (s[i1] == t[j]) {
                i1++;
            }

            i1 = max(i1, i0 + 1);

            if (s[i0] == t[j]) {
                i0++;
            }

            j++;
        }

        return i1 == m;
    }
};
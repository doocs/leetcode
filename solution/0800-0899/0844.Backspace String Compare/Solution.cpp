class Solution {
public:
    bool backspaceCompare(string s, string t) {
        int i = s.size() - 1, j = t.size() - 1;
        int skip1 = 0, skip2 = 0;
        for (; i >= 0 || j >= 0; --i, --j) {
            while (i >= 0) {
                if (s[i] == '#') {
                    ++skip1;
                    --i;
                } else if (skip1) {
                    --skip1;
                    --i;
                } else
                    break;
            }
            while (j >= 0) {
                if (t[j] == '#') {
                    ++skip2;
                    --j;
                } else if (skip2) {
                    --skip2;
                    --j;
                } else
                    break;
            }
            if (i >= 0 && j >= 0) {
                if (s[i] != t[j]) return false;
            } else if (i >= 0 || j >= 0)
                return false;
        }
        return true;
    }
};
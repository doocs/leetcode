class Solution {
public:
    bool match(string s, string p, int sl, int pl) {
        if (sl == s.size() && pl == p.size()) {
            return true;
        }

        if (sl < s.size() && pl == p.size()) {
            return false;
        }

        if (p[pl+1] != '*') {
            // 如果p的下一个不是*的情况
            if ((s[sl] == p[pl]) || (sl<s.size() && p[pl] == '.')) {
                return match(s, p, sl+1, pl+1);
            } else {
                return false;
            }
        } else {
            if ((s[sl] == p[pl]) || (sl<s.size() && p[pl] == '.')) {
                return match(s, p, sl, pl+2) || match(s, p, sl+1, pl);
            } else {
                return match(s, p, sl, pl+2);
            }
        }
    }

    bool isMatch(string s, string p) {
        return match(s, p, 0, 0);
    }
};

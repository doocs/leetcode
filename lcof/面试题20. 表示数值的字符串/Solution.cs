public class Solution {
    public bool IsNumber(string s) {
        if (s == null || s.Trim() == null) {
            return false;
        }
        s = s.Trim();
        bool findNum = false, findDot = false, findE = false;
        for (int i = 0; i < s.Length; i++) {
            if (s[i] == '+' || s[i] == '-') {
                if (i != 0 && s[i-1] != 'e' && s[i-1] != 'E') {
                    return false;
                }
            } else if (s[i] >= '0' && s[i] <= '9') {
                findNum = true;
            } else if (s[i] == '.') {
                if (findDot || findE) {
                    return false;
                }
                findDot = true;
            } else if (s[i] == 'e' || s[i] == 'E') {
                if (!findNum || findE) {
                    return false;
                }
                findE = true;
                findNum = false;
            } else {
                return false;
            }
        }
        return findNum;
    }
}
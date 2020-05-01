class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        char[] chars = s.trim().toCharArray();
        boolean findNum = false;
        boolean findE = false;
        boolean findDot = false;
        for (int i = 0, n = chars.length; i < n; ++i) {
            if (chars[i] == '+' || chars[i] == '-') {
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') {
                    return false;
                }
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                findNum = true;
            } else if (chars[i] == '.') {
                if (findDot || findE) {
                    return false;
                }
                findDot = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                if (findE || !findNum) {
                    return false;
                }
                findE = true;
                findNum = false; // 确保e之后也出现数
            } else {
                return false;
            }
        }
        return findNum;
    }
}
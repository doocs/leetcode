class Solution {
    public boolean isNumber(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == ' ') {
            ++i;
        }
        while (i <= j && s.charAt(j) == ' ') {
            --j;
        }
        if (i > j) {
            return false;
        }
        boolean digit = false;
        boolean dot = false;
        boolean e = false;
        for (; i <= j; ++i) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i > 0 && s.charAt(i - 1) != ' ' && s.charAt(i - 1) != 'e'
                    && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (Character.isDigit(s.charAt(i))) {
                digit = true;
            } else if (s.charAt(i) == '.') {
                if (dot || e) {
                    return false;
                }
                dot = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (!digit || e) {
                    return false;
                }
                e = true;
                digit = false;
            } else {
                return false;
            }
        }
        return digit;
    }
}
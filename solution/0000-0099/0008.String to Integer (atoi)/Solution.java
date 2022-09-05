class Solution {
    public int myAtoi(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n == 0) return 0;
        int i = 0;
        while (s.charAt(i) == ' ') {
            if (++i == n) return 0;
        }
        int sign = 1;
        if (s.charAt(i) == '-') sign = -1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') ++i;
        int res = 0, flag = Integer.MAX_VALUE / 10;
        for (int j = i; j < n; ++j) {
            if (s.charAt(j) < '0' || s.charAt(j) > '9') break;
            if (res > flag || (res == flag && s.charAt(j) > '7'))
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (s.charAt(j) - '0');
        }
        return sign * res;
    }
}
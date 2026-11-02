class Solution {
    public int strToInt(String str) {
        if (str == null) return 0;
        int n = str.length();
        if (n == 0) return 0;
        int i = 0;
        while (str.charAt(i) == ' ') {
            if (++i == n) return 0;
        }
        int sign = 1;
        if (str.charAt(i) == '-') sign = -1;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') ++i;
        int res = 0, flag = Integer.MAX_VALUE / 10;
        for (; i < n; ++i) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
            if (res > flag || (res == flag) && str.charAt(i) > '7')
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (str.charAt(i) - '0');
        }
        return sign * res;
    }
}
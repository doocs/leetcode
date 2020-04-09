class Solution {
    public int strToInt(String str) {
        if (str == null || "".equals(str.trim())) {
            return 0;
        }
        str = str.trim();
        int res = 0, i = 0, flag = 1;
        int n = str.length();
        if (str.charAt(i) == '-') {
            flag = -1;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            ++i;
        }
        while (i < n && Character.isDigit(str.charAt(i))) {
            int r = str.charAt(i) - '0';
            // 溢出处理
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10) && r > 7) {
                return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + r;
            ++i;
        }

        return flag > 0 ? res : -res;
    }
}
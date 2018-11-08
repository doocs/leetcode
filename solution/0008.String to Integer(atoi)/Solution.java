class Solution {
    public int myAtoi(String str) {
        int len = str.length();
        if (len == 0) return 0;
        char[] cs = str.toCharArray();
        int i = 0;
        while (i < len && cs[i] == ' ') i++;
        if (i==len) return 0;
        char c1 = cs[i];
        int sig = 1;
        if ((c1 > '9' || c1 < '0')) {
            if (c1 == '-') {
                sig = -1;
                i++;
            } else if (c1 == '+') {
                i++;
            } else return 0;
        }
        long v = 0,sv = 0;
        for (; i < len; i++) {
            char c = cs[i];
            if (c < '0' || c > '9') break;
            v = v * 10 + (c - '0');
            sv = v * sig;
            if (sv > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            else if (sv < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) sv;
    }
}

class Solution {
    public String removeKdigits(String num, int k) {
        if (k <= 0) {
            return num;
        }
        if (num.length() <= k) {
            return "0";
        }
        int len = num.length() - k;
        char[] cs = new char[num.length()];
        int top = -1;
        for (char c : num.toCharArray()) {
            while (top >= 0 && cs[top] > c && k > 0) {
                --top;
                --k;
            }
            cs[++top] = c;
        }
        int offset = 0;
        while (offset <= top && cs[offset] == '0') {
            ++offset;
        }
        return offset > top ? "0" : new String(cs, offset, len - offset);
    }
}

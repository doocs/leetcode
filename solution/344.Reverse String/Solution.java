class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int p = 0;
        int q = chars.length - 1;
        while (p < q) {
            char tmp = chars[p];
            chars[p] = chars[q];
            chars[q] = tmp;
            ++p;
            --q;
        }
        return String.valueOf(chars);
    }

}
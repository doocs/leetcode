class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += (k << 1)) {
            for (int st = i, ed = Math.min(chars.length - 1, i + k - 1); st < ed; ++st, --ed) {
                char t = chars[st];
                chars[st] = chars[ed];
                chars[ed] = t;
            }
        }
        return new String(chars);
    }
}
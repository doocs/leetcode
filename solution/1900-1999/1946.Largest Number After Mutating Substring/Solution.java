class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] s = num.toCharArray();
        for (int i = 0; i < s.length; ++i) {
            if (change[s[i] - '0'] > s[i] - '0') {
                for (; i < s.length && s[i] - '0' <= change[s[i] - '0']; ++i) {
                    s[i] = (char) (change[s[i] - '0'] + '0');
                }
                break;
            }
        }
        return String.valueOf(s);
    }
}
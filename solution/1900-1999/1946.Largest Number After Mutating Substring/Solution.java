class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] s = num.toCharArray();
        boolean changed = false;
        for (int i = 0; i < s.length; ++i) {
            char d = (char) (change[s[i] - '0'] + '0');
            if (changed && d < s[i]) {
                break;
            }
            if (d > s[i]) {
                changed = true;
                s[i] = d;
            }
        }
        return new String(s);
    }
}

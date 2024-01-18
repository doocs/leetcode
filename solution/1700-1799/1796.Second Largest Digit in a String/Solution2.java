class Solution {
    public int secondHighest(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                mask |= 1 << (c - '0');
            }
        }
        for (int i = 9, cnt = 0; i >= 0; --i) {
            if (((mask >> i) & 1) == 1 && ++cnt == 2) {
                return i;
            }
        }
        return -1;
    }
}
class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.length(); ++i) {
            char a = s1.charAt(i), b = s2.charAt(i);
            if (a < b) {
                ++xy;
            }
            if (a > b) {
                ++yx;
            }
        }
        if ((xy + yx) % 2 == 1) {
            return -1;
        }
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
}
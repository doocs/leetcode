class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0, yx = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] > c2[i]) {
                xy++;
            }
            if (c2[i] > c1[i]) {
                yx++;
            }
        }
        if ((xy + yx) % 2 != 0) {
            return -1;
        }
        return xy % 2 == 0 ? (xy + yx) / 2 : (xy + yx) / 2 + 1;
    }
}
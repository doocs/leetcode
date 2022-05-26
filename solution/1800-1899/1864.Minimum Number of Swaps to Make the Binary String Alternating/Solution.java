class Solution {
    public int minSwaps(String s) {
        int s0n0 = 0, s0n1 = 0;
        int s1n0 = 0, s1n1 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if ((i & 1) == 0) {
                if (s.charAt(i) != '0') {
                    s0n0 += 1;
                } else {
                    s1n1 += 1;
                }
            } else {
                if (s.charAt(i) != '0') {
                    s1n0 += 1;
                } else {
                    s0n1 += 1;
                }
            }
        }
        if (s0n0 != s0n1 && s1n0 != s1n1) {
            return -1;
        }
        if (s0n0 != s0n1) {
            return s1n0;
        }
        if (s1n0 != s1n1) {
            return s0n0;
        }
        return Math.min(s0n0, s1n0);
    }
}
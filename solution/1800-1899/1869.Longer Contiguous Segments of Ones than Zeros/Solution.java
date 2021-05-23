class Solution {
    public boolean checkZeroOnes(String s) {
        int len0 = 0, len1 = 0;
        int t0 = 0, t1 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                t0 += 1;
                t1 = 0;
            } else {
                t0 = 0;
                t1 += 1;
            }
            len0 = Math.max(len0, t0);
            len1 = Math.max(len1, t1);
        }
        return len1 > len0;
    }
}
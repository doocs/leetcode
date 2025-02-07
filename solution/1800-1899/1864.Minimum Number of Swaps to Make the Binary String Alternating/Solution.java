class Solution {
    private char[] s;

    public int minSwaps(String s) {
        this.s = s.toCharArray();
        int n1 = 0;
        for (char c : this.s) {
            n1 += (c - '0');
        }
        int n0 = this.s.length - n1;
        if (Math.abs(n0 - n1) > 1) {
            return -1;
        }
        if (n0 == n1) {
            return Math.min(calc(0), calc(1));
        }
        return calc(n0 > n1 ? 0 : 1);
    }

    private int calc(int c) {
        int cnt = 0;
        for (int i = 0; i < s.length; ++i) {
            int x = s[i] - '0';
            if ((i & 1 ^ c) != x) {
                ++cnt;
            }
        }
        return cnt / 2;
    }
}

class Solution {
    public int minimumFlips(int n) {
        String s = Integer.toBinaryString(n);
        int m = s.length();
        int cnt = 0;
        for (int i = 0; i < m / 2; i++) {
            if (s.charAt(i) != s.charAt(m - i - 1)) {
                cnt++;
            }
        }
        return cnt * 2;
    }
}

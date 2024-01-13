class Solution {
    public boolean checkZeroOnes(String s) {
        return f(s, '1') > f(s, '0');
    }

    private int f(String s, char x) {
        int cnt = 0, mx = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == x) {
                mx = Math.max(mx, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return mx;
    }
}
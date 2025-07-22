class Solution {
    private char[] s;
    private int k;

    public int maxDistance(String s, int k) {
        this.s = s.toCharArray();
        this.k = k;
        int a = calc('S', 'E');
        int b = calc('S', 'W');
        int c = calc('N', 'E');
        int d = calc('N', 'W');
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private int calc(char a, char b) {
        int ans = 0, mx = 0, cnt = 0;
        for (char c : s) {
            if (c == a || c == b) {
                ++mx;
            } else if (cnt < k) {
                ++mx;
                ++cnt;
            } else {
                --mx;
            }
            ans = Math.max(ans, mx);
        }
        return ans;
    }
}

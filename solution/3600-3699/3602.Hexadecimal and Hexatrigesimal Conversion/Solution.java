class Solution {
    public String concatHex36(int n) {
        int x = n * n;
        int y = n * n * n;
        return f(x, 16) + f(y, 36);
    }

    private String f(int x, int k) {
        StringBuilder res = new StringBuilder();
        while (x > 0) {
            int v = x % k;
            if (v <= 9) {
                res.append((char) ('0' + v));
            } else {
                res.append((char) ('A' + v - 10));
            }
            x /= k;
        }
        return res.reverse().toString();
    }
}

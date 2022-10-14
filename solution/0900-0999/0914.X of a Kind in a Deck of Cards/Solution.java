class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] cnt = new int[10000];
        for (int v : deck) {
            ++cnt[v];
        }
        int g = -1;
        for (int v : cnt) {
            if (v > 0) {
                g = g == -1 ? v : gcd(g, v);
            }
        }
        return g >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
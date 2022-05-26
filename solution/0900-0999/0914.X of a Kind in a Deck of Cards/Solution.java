class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] counter = new int[10000];
        for (int d : deck) {
            ++counter[d];
        }
        int g = -1;
        for (int v : counter) {
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
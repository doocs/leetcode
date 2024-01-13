class Solution {
    private int[] cont;

    public int[] fraction(int[] cont) {
        this.cont = cont;
        return dfs(0);
    }

    private int[] dfs(int i) {
        if (i == cont.length - 1) {
            return new int[] {cont[i], 1};
        }
        int[] next = dfs(i + 1);
        int a = next[0], b = next[1];
        int x = a * cont[i] + b, y = a;
        int g = gcd(x, y);
        return new int[] {x / g, y / g};
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
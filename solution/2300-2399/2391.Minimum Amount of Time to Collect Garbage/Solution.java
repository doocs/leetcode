class Solution {
    private String[] g;
    private int[] travel;

    public int garbageCollection(String[] garbage, int[] travel) {
        g = garbage;
        this.travel = travel;
        return f('M') + f('P') + f('G');
    }

    private int f(char c) {
        int tot = 0;
        for (var v : g) {
            for (char ch : v.toCharArray()) {
                if (ch == c) {
                    ++tot;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < g.length; ++i) {
            int t = 0;
            for (char ch : g[i].toCharArray()) {
                if (ch == c) {
                    ++t;
                }
            }
            res += t;
            tot -= t;
            if (tot == 0) {
                break;
            }
            if (i < g.length - 1) {
                res += travel[i];
            }
        }
        return res;
    }
}
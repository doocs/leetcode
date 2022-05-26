class Solution {
    private int[] p;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        boolean[] ans = new boolean[requests.length];
        int i = 0;
        for (int[] req : requests) {
            int u = req[0], v = req[1];
            if (find(u) == find(v)) {
                ans[i++] = true;
            } else {
                boolean valid = true;
                for (int[] res : restrictions) {
                    int x = res[0], y = res[1];
                    if ((find(u) == find(x) && find(v) == find(y)) || (find(u) == find(y) && find(v) == find(x))) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    p[find(u)] = find(v);
                    ans[i++] = true;
                } else {
                    ans[i++] = false;
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
class Solution {
    private int[] p;

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(strs[i], strs[j])) {
                    p[find(i)] = find(j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++res;
            }
        }
        return res;
    }

    private boolean check(String a, String b) {
        int cnt = 0;
        int n = a.length();
        for (int i = 0; i < n; ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ++cnt;
            }
        }
        return cnt <= 2;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
class Solution {
    private int[] p;
    private double[] w;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        p = new int[n << 1];
        w = new double[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            w[i] = 1.0;
        }
        Map<String, Integer> mp = new HashMap<>(n << 1);
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            List<String> e = equations.get(i);
            String a = e.get(0), b = e.get(1);
            if (!mp.containsKey(a)) {
                mp.put(a, idx++);
            }
            if (!mp.containsKey(b)) {
                mp.put(b, idx++);
            }
            int pa = find(mp.get(a)), pb = find(mp.get(b));
            if (pa == pb) {
                continue;
            }
            p[pa] = pb;
            w[pa] = w[mp.get(b)] * values[i] / w[mp.get(a)];
        }
        int m = queries.size();
        double[] res = new double[m];
        for (int i = 0; i < m; ++i) {
            String c = queries.get(i).get(0), d = queries.get(i).get(1);
            Integer id1 = mp.get(c), id2 = mp.get(d);
            if (id1 == null || id2 == null) {
                res[i] = -1.0;
            } else {
                int pa = find(id1), pb = find(id2);
                res[i] = pa == pb ? w[id1] / w[id2] : -1.0;
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            int origin = p[x];
            p[x] = find(p[x]);
            w[x] *= w[origin];
        }
        return p[x];
    }
}
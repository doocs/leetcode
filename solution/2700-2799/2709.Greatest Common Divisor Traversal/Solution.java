class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    private static final int MX = 100010;
    private static final List<Integer>[] P = new List[MX];

    static {
        Arrays.setAll(P, k -> new ArrayList<>());
        for (int x = 1; x < MX; ++x) {
            int v = x;
            int i = 2;
            while (i <= v / i) {
                if (v % i == 0) {
                    P[x].add(i);
                    while (v % i == 0) {
                        v /= i;
                    }
                }
                ++i;
            }
            if (v > 1) {
                P[x].add(v);
            }
        }
    }

    public boolean canTraverseAllPairs(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt();
        int n = nums.length;
        UnionFind uf = new UnionFind(n + m + 1);
        for (int i = 0; i < n; ++i) {
            for (int j : P[nums[i]]) {
                uf.union(i, j + n);
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            s.add(uf.find(i));
        }
        return s.size() == 1;
    }
}
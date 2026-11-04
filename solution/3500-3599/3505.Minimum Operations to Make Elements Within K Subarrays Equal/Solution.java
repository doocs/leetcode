class MedianCost {
    TreeMap<Integer, Integer> l = new TreeMap<>();
    TreeMap<Integer, Integer> r = new TreeMap<>();
    int n1 = 0, n2 = 0;
    long s1 = 0, s2 = 0;

    void addTo(TreeMap<Integer, Integer> tree, int x) {
        tree.merge(x, 1, Integer::sum);
    }

    void removeFrom(TreeMap<Integer, Integer> tree, int x) {
        int cnt = tree.get(x);
        if (cnt == 1) {
            tree.remove(x);
        } else {
            tree.put(x, cnt - 1);
        }
    }

    void adjust() {
        while (n1 - n2 > 1) {
            int x = l.lastKey();
            removeFrom(l, x);
            n1--;
            s1 -= x;
            addTo(r, x);
            n2++;
            s2 += x;
        }
        while (n2 > n1) {
            int x = r.firstKey();
            removeFrom(r, x);
            n2--;
            s2 -= x;
            addTo(l, x);
            n1++;
            s1 += x;
        }
    }

    void add(int x) {
        addTo(r, x);
        n2++;
        s2 += x;

        int y = r.firstKey();
        removeFrom(r, y);
        n2--;
        s2 -= y;
        addTo(l, y);
        n1++;
        s1 += y;

        adjust();
    }

    void remove(int x) {
        if (l.containsKey(x)) {
            removeFrom(l, x);
            n1--;
            s1 -= x;
        } else {
            removeFrom(r, x);
            n2--;
            s2 -= x;
        }
        adjust();
    }

    long query() {
        long mid = l.lastKey();
        return n1 * mid - s1 + s2 - n2 * mid;
    }
}

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long INF = Long.MAX_VALUE / 4;
        long[][] f = new long[n + 1][k + 1];
        for (long[] row : f) {
            Arrays.fill(row, INF);
        }
        f[0][0] = 0;

        MedianCost mc = new MedianCost();
        for (int i = 1; i <= n; i++) {
            mc.add(nums[i - 1]);
            long w = mc.query();

            for (int j = 0; j <= k; j++) {
                f[i][j] = f[i - 1][j];
                if (j > 0 && i >= x) {
                    f[i][j] = Math.min(f[i][j], f[i - x][j - 1] + w);
                }
            }

            if (i >= x) {
                mc.remove(nums[i - x]);
            }
        }
        return f[n][k];
    }
}
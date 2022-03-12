class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : obstacles) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int n = obstacles.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = obstacles[i];
            int x = m.get(v);
            ans[i] = tree.query(x) + 1;
            tree.update(x, ans[i]);
        }
        return ans;
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
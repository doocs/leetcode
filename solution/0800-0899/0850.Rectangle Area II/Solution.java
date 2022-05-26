class Node {
    int l;
    int r;
    int cnt;
    int len;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        int n = nums.length - 1;
        this.nums = nums;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 0, n - 1);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    public void modify(int u, int l, int r, int k) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].cnt += k;
        } else {
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r, k);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r, k);
            }
        }
        pushup(u);
    }

    public int query() {
        return tr[1].len;
    }

    public void pushup(int u) {
        if (tr[u].cnt > 0) {
            tr[u].len = nums[tr[u].r + 1] - nums[tr[u].l];
        } else if (tr[u].l == tr[u].r) {
            tr[u].len = 0;
        } else {
            tr[u].len = tr[u << 1].len + tr[u << 1 | 1].len;
        }
    }
}

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length;
        int[][] segs = new int[n << 1][4];
        int idx = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] rect : rectangles) {
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
            segs[idx++] = new int[]{x1, y1, y2, 1};
            segs[idx++] = new int[]{x2, y1, y2, -1};
            ts.add(y1);
            ts.add(y2);
        }
        Map<Integer, Integer> m = new HashMap<>();
        int[] nums = new int[ts.size()];
        idx = 0;
        for (int v : ts) {
            nums[idx] = v;
            m.put(v, idx++);
        }
        Arrays.sort(segs, Comparator.comparingInt(a -> a[0]));
        SegmentTree tree = new SegmentTree(nums);
        long ans = 0;
        for (int i = 0; i < segs.length; ++i) {
            int x = segs[i][0], y1 = segs[i][1], y2 = segs[i][2], k = segs[i][3];
            if (i > 0) {
                ans += (long) tree.query() * (x - segs[i - 1][0]);
            }
            tree.modify(1, m.get(y1), m.get(y2) - 1, k);
        }
        ans %= MOD;
        return (int) ans;
    }
}
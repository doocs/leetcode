class Solution {
    private static final int MOD = 1000000007;

    static class Node {
        int l, r;
        long sum, sumSq;
        int lazy;
    }

    static class SegmentTree {
        Node[] tr;

        SegmentTree(int n) {
            tr = new Node[n << 2];
            for (int i = 0; i < tr.length; i++) {
                tr[i] = new Node();
            }
            build(1, 0, n - 1);
        }

        void build(int u, int l, int r) {
            tr[u].l = l;
            tr[u].r = r;
            if (l == r) {
                return;
            }
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }

        void apply(int u, int v) {
            long len = tr[u].r - tr[u].l + 1L;
            long add = v;
            tr[u].sumSq += 2 * add * tr[u].sum + add * add * len;
            tr[u].sum += add * len;
            tr[u].lazy += v;
        }

        void pushdown(int u) {
            if (tr[u].lazy != 0) {
                apply(u << 1, tr[u].lazy);
                apply(u << 1 | 1, tr[u].lazy);
                tr[u].lazy = 0;
            }
        }

        void pushup(int u) {
            tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
            tr[u].sumSq = tr[u << 1].sumSq + tr[u << 1 | 1].sumSq;
        }

        void modify(int u, int l, int r, int v) {
            if (tr[u].l >= l && tr[u].r <= r) {
                apply(u, v);
                return;
            }
            pushdown(u);
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r, v);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r, v);
            }
            pushup(u);
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = last.getOrDefault(nums[i], -1);
            st.modify(1, p + 1, i, 1);
            last.put(nums[i], i);
            ans = (ans + st.tr[1].sumSq) % MOD;
        }
        return (int) ans;
    }
}

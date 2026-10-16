public class Solution {
    private const int Mod = 1000000007;

    private class Node {
        public int L, R;
        public long Sum, SumSq;
        public int Lazy;
    }

    private class SegmentTree {
        private readonly Node[] tr;

        public SegmentTree(int n) {
            tr = new Node[n << 2];
            for (int i = 0; i < tr.Length; i++) {
                tr[i] = new Node();
            }
            Build(1, 0, n - 1);
        }

        public long SumSq => tr[1].SumSq;

        private void Build(int u, int l, int r) {
            tr[u].L = l;
            tr[u].R = r;
            if (l == r) {
                return;
            }
            int mid = (l + r) >> 1;
            Build(u << 1, l, mid);
            Build(u << 1 | 1, mid + 1, r);
        }

        private void Apply(int u, int v) {
            long len = tr[u].R - tr[u].L + 1L;
            long add = v;
            tr[u].SumSq += 2 * add * tr[u].Sum + add * add * len;
            tr[u].Sum += add * len;
            tr[u].Lazy += v;
        }

        private void PushDown(int u) {
            if (tr[u].Lazy != 0) {
                Apply(u << 1, tr[u].Lazy);
                Apply(u << 1 | 1, tr[u].Lazy);
                tr[u].Lazy = 0;
            }
        }

        private void PushUp(int u) {
            tr[u].Sum = tr[u << 1].Sum + tr[u << 1 | 1].Sum;
            tr[u].SumSq = tr[u << 1].SumSq + tr[u << 1 | 1].SumSq;
        }

        public void Modify(int u, int l, int r, int v) {
            if (tr[u].L >= l && tr[u].R <= r) {
                Apply(u, v);
                return;
            }
            PushDown(u);
            int mid = (tr[u].L + tr[u].R) >> 1;
            if (l <= mid) {
                Modify(u << 1, l, r, v);
            }
            if (r > mid) {
                Modify(u << 1 | 1, l, r, v);
            }
            PushUp(u);
        }
    }

    public int SumCounts(int[] nums) {
        int n = nums.Length;
        SegmentTree st = new SegmentTree(n);
        Dictionary<int, int> last = new Dictionary<int, int>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = last.TryGetValue(nums[i], out int idx) ? idx : -1;
            st.Modify(1, p + 1, i, 1);
            last[nums[i]] = i;
            ans = (ans + st.SumSq) % Mod;
        }
        return (int) ans;
    }
}

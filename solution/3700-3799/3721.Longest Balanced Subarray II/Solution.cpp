/*
 * Segment tree node.
 * It maintains:
 *  - [l, r] : the covered index range
 *  - mn     : minimum prefix sum in this range
 *  - mx     : maximum prefix sum in this range
 *  - lazy   : lazy propagation value (range add)
 */
class Node {
public:
    int l = 0, r = 0;
    int mn = 0, mx = 0;
    int lazy = 0;
};

/*
 * Segment Tree that supports:
 *  1. Range add
 *  2. Query the smallest index whose prefix sum equals a given value
 */
class SegmentTree {
public:
    SegmentTree(int n) {
        tr.resize(n << 2);
        for (int i = 0; i < (int) tr.size(); ++i) {
            tr[i] = new Node();
        }
        // Build the tree on range [0, n]
        build(1, 0, n);
    }

    /*
     * Add value v to all prefix sums in range [l, r]
     */
    void modify(int u, int l, int r, int v) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            apply(u, v);
            return;
        }
        pushdown(u);
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (l <= mid) modify(u << 1, l, r, v);
        if (r > mid) modify(u << 1 | 1, l, r, v);
        pushup(u);
    }

    /*
     * Binary search on the segment tree.
     * Find the smallest index pos such that prefix_sum[pos] == target.
     *
     * The key observation:
     * If target is within [mn, mx] of a segment, then such a position
     * must exist inside this segment.
     */
    int query(int u, int target) {
        if (tr[u]->l == tr[u]->r) {
            return tr[u]->l;
        }
        pushdown(u);
        int lc = u << 1, rc = u << 1 | 1;
        if (tr[lc]->mn <= target && target <= tr[lc]->mx) {
            return query(lc, target);
        }
        return query(rc, target);
    }

private:
    vector<Node*> tr;

    /*
     * Build an empty segment tree.
     * Initial prefix sums are all zero.
     */
    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        tr[u]->mn = tr[u]->mx = 0;
        tr[u]->lazy = 0;
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    /*
     * Apply a range add to node u.
     */
    void apply(int u, int v) {
        tr[u]->mn += v;
        tr[u]->mx += v;
        tr[u]->lazy += v;
    }

    /*
     * Push information from children to parent.
     */
    void pushup(int u) {
        tr[u]->mn = min(tr[u << 1]->mn, tr[u << 1 | 1]->mn);
        tr[u]->mx = max(tr[u << 1]->mx, tr[u << 1 | 1]->mx);
    }

    /*
     * Push lazy tag down to children.
     */
    void pushdown(int u) {
        if (tr[u]->lazy != 0) {
            apply(u << 1, tr[u]->lazy);
            apply(u << 1 | 1, tr[u]->lazy);
            tr[u]->lazy = 0;
        }
    }
};

class Solution {
public:
    int longestBalanced(vector<int>& nums) {
        int n = nums.size();
        SegmentTree st(n);

        /*
         * last[x] = the most recent position where value x appeared
         */
        unordered_map<int, int> last;

        int now = 0; // current prefix sum
        int ans = 0; // answer

        /*
         * Enumerate the right endpoint of the subarray
         */
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];

            /*
             * Contribution of x:
             *  +1 if x is odd
             *  -1 if x is even
             */
            int det = (x & 1) ? 1 : -1;

            /*
             * If x appeared before, remove its previous contribution
             */
            if (last.count(x)) {
                st.modify(1, last[x], n, -det);
                now -= det;
            }

            /*
             * Add current contribution of x
             */
            last[x] = i;
            st.modify(1, i, n, det);
            now += det;

            /*
             * Find the smallest position pos such that
             * prefix_sum[pos] == now
             */
            int pos = st.query(1, now);

            /*
             * Update the maximum balanced subarray length
             */
            ans = max(ans, i - pos);
        }

        return ans;
    }
};

class Node {
public:
    int l = 0, r = 0;
    long long sum = 0, sumSq = 0;
    int lazy = 0;
};

class SegmentTree {
public:
    SegmentTree(int n) {
        tr.resize(n << 2);
        build(1, 0, n - 1);
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

    long long sumSq() const {
        return tr[1].sumSq;
    }

private:
    vector<Node> tr;

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
        long long len = tr[u].r - tr[u].l + 1LL;
        long long add = v;
        tr[u].sumSq += 2 * add * tr[u].sum + add * add * len;
        tr[u].sum += add * len;
        tr[u].lazy += v;
    }

    void pushdown(int u) {
        if (tr[u].lazy) {
            apply(u << 1, tr[u].lazy);
            apply(u << 1 | 1, tr[u].lazy);
            tr[u].lazy = 0;
        }
    }

    void pushup(int u) {
        tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum;
        tr[u].sumSq = tr[u << 1].sumSq + tr[u << 1 | 1].sumSq;
    }
};

class Solution {
public:
    int sumCounts(vector<int>& nums) {
        int n = nums.size();
        SegmentTree st(n);
        unordered_map<int, int> last;
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int p = last.count(nums[i]) ? last[nums[i]] : -1;
            st.modify(1, p + 1, i, 1);
            last[nums[i]] = i;
            ans = (ans + st.sumSq()) % 1000000007;
        }
        return (int) ans;
    }
};

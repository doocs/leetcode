struct Node {
    int l = 0, r = 0, cnt = 0;
    int length = 0;
};

class SegmentTree {
private:
    vector<Node> tr;
    vector<int> nums;

    void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    void pushup(int u) {
        if (tr[u].cnt > 0) {
            tr[u].length = nums[tr[u].r + 1] - nums[tr[u].l];
        } else if (tr[u].l == tr[u].r) {
            tr[u].length = 0;
        } else {
            tr[u].length = tr[u << 1].length + tr[u << 1 | 1].length;
        }
    }

public:
    SegmentTree(const vector<int>& nums)
        : nums(nums) {
        int n = (int) nums.size() - 1;
        tr.assign(n << 2, Node());
        build(1, 0, n - 1);
    }

    void modify(int u, int l, int r, int k) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].cnt += k;
        } else {
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) modify(u << 1, l, r, k);
            if (r > mid) modify(u << 1 | 1, l, r, k);
        }
        pushup(u);
    }

    int query() const {
        return tr[1].length;
    }
};

class Solution {
public:
    double separateSquares(vector<vector<int>>& squares) {
        set<int> xs;
        vector<array<int, 4>> segs;

        for (auto& sq : squares) {
            int x1 = sq[0], y1 = sq[1], l = sq[2];
            int x2 = x1 + l, y2 = y1 + l;
            xs.insert(x1);
            xs.insert(x2);
            segs.push_back({y1, x1, x2, 1});
            segs.push_back({y2, x1, x2, -1});
        }

        sort(segs.begin(), segs.end(), [](const auto& a, const auto& b) {
            return a[0] < b[0];
        });

        vector<int> st;
        st.reserve(xs.size());
        for (int x : xs) st.push_back(x);

        SegmentTree tree(st);

        unordered_map<int, int> d;
        d.reserve(st.size() * 2);
        for (int i = 0; i < (int) st.size(); i++) d[st[i]] = i;

        double area = 0.0;
        int y0 = 0;
        for (auto& s : segs) {
            int y = s[0], x1 = s[1], x2 = s[2], k = s[3];
            area += (double) (y - y0) * tree.query();
            tree.modify(1, d[x1], d[x2] - 1, k);
            y0 = y;
        }

        double target = area / 2.0;
        area = 0.0;
        y0 = 0;
        for (auto& s : segs) {
            int y = s[0], x1 = s[1], x2 = s[2], k = s[3];
            double t = (double) (y - y0) * tree.query();
            if (area + t >= target) {
                return y0 + (target - area) / tree.query();
            }
            area += t;
            tree.modify(1, d[x1], d[x2] - 1, k);
            y0 = y;
        }

        return 0.0;
    }
};

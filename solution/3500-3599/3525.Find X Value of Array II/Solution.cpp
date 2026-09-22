class Node {
public:
    int l = 0, r = 0;
    int prod = 1;
    int cnt[5]{};
};

class SegmentTree {
public:
    SegmentTree(vector<int>& nums, int k) {
        this->k = k;
        int n = nums.size();
        tr.resize(n << 2);
        build(1, 1, n, nums);
    }

    void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) {
            v %= k;
            tr[u].prod = v;
            memset(tr[u].cnt, 0, sizeof(tr[u].cnt));
            tr[u].cnt[v] = 1;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u];
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        return merge(query(u << 1, l, r), query(u << 1 | 1, l, r));
    }

private:
    int k;
    vector<Node> tr;

    Node merge(const Node& a, const Node& b) {
        Node c;
        c.prod = a.prod * b.prod % k;
        memcpy(c.cnt, a.cnt, sizeof(c.cnt));
        for (int r = 0; r < k; ++r) {
            c.cnt[a.prod * r % k] += b.cnt[r];
        }
        return c;
    }

    void pushup(int u) {
        Node p = merge(tr[u << 1], tr[u << 1 | 1]);
        tr[u].prod = p.prod;
        memcpy(tr[u].cnt, p.cnt, sizeof(tr[u].cnt));
    }

    void build(int u, int l, int r, vector<int>& nums) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            int v = nums[l - 1] % k;
            tr[u].prod = v;
            tr[u].cnt[v] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid, nums);
        build(u << 1 | 1, mid + 1, r, nums);
        pushup(u);
    }
};

class Solution {
public:
    vector<int> resultArray(vector<int>& nums, int k, vector<vector<int>>& queries) {
        int n = nums.size();
        SegmentTree tree(nums, k);
        vector<int> ans;
        ans.reserve(queries.size());
        for (auto& q : queries) {
            tree.modify(1, q[0] + 1, q[1]);
            ans.push_back(tree.query(1, q[2] + 1, n).cnt[q[3]]);
        }
        return ans;
    }
};

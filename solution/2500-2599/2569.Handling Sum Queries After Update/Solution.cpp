class Node {
public:
    int l = 0, r = 0;
    int s = 0, lazy = 0;
};

class SegmentTree {
public:
    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    void modify(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            tr[u]->lazy ^= 1;
            tr[u]->s = tr[u]->r - tr[u]->l + 1 - tr[u]->s;
            return;
        }
        pushdown(u);
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r);
        }
        if (r > mid) {
            modify(u << 1 | 1, l, r);
        }
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return tr[u]->s;
        }
        pushdown(u);
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int res = 0;
        if (l <= mid) {
            res += query(u << 1, l, r);
        }
        if (r > mid) {
            res += query(u << 1 | 1, l, r);
        }
        return res;
    }

private:
    vector<Node*> tr;
    vector<int> nums;

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->s = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        tr[u]->s = tr[u << 1]->s + tr[u << 1 | 1]->s;
    }

    void pushdown(int u) {
        if (tr[u]->lazy) {
            int mid = (tr[u]->l + tr[u]->r) >> 1;
            tr[u << 1]->s = mid - tr[u]->l + 1 - tr[u << 1]->s;
            tr[u << 1]->lazy ^= 1;
            tr[u << 1 | 1]->s = tr[u]->r - mid - tr[u << 1 | 1]->s;
            tr[u << 1 | 1]->lazy ^= 1;
            tr[u]->lazy ^= 1;
        }
    }
};

class Solution {
public:
    vector<long long> handleQuery(vector<int>& nums1, vector<int>& nums2, vector<vector<int>>& queries) {
        SegmentTree* tree = new SegmentTree(nums1);
        long long s = 0;
        for (int& x : nums2) {
            s += x;
        }
        vector<long long> ans;
        for (auto& q : queries) {
            if (q[0] == 1) {
                tree->modify(1, q[1] + 1, q[2] + 1);
            } else if (q[0] == 2) {
                s += 1LL * q[1] * tree->query(1, 1, nums1.size());
            } else {
                ans.push_back(s);
            }
        }
        return ans;
    }
};
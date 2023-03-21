class Node {
public:
    int l = 0, r = 0;
    int x = 0, cnt = 0;
};

using pii = pair<int, int>;

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

    pii query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return {tr[u]->x, tr[u]->cnt};
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        auto left = query(u << 1, l, r);
        auto right = query(u << 1 | 1, l, r);
        if (left.first == right.first) {
            left.second += right.second;
        } else if (left.second >= right.second) {
            left.second -= right.second;
        } else {
            right.second -= left.second;
            left = right;
        }
        return left;
    }

private:
    vector<Node*> tr;
    vector<int> nums;

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->x = nums[l - 1];
            tr[u]->cnt = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        if (tr[u << 1]->x == tr[u << 1 | 1]->x) {
            tr[u]->x = tr[u << 1]->x;
            tr[u]->cnt = tr[u << 1]->cnt + tr[u << 1 | 1]->cnt;
        } else if (tr[u << 1]->cnt >= tr[u << 1 | 1]->cnt) {
            tr[u]->x = tr[u << 1]->x;
            tr[u]->cnt = tr[u << 1]->cnt - tr[u << 1 | 1]->cnt;
        } else {
            tr[u]->x = tr[u << 1 | 1]->x;
            tr[u]->cnt = tr[u << 1 | 1]->cnt - tr[u << 1]->cnt;
        }
    }
};

class MajorityChecker {
public:
    MajorityChecker(vector<int>& arr) {
        tree = new SegmentTree(arr);
        for (int i = 0; i < arr.size(); ++i) {
            d[arr[i]].push_back(i);
        }
    }

    int query(int left, int right, int threshold) {
        int x = tree->query(1, left + 1, right + 1).first;
        auto l = lower_bound(d[x].begin(), d[x].end(), left);
        auto r = lower_bound(d[x].begin(), d[x].end(), right + 1);
        return r - l >= threshold ? x : -1;
    }

private:
    unordered_map<int, vector<int>> d;
    SegmentTree* tree;
};

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker* obj = new MajorityChecker(arr);
 * int param_1 = obj->query(left,right,threshold);
 */
class Node {
public:
    int l, r, cnt, len;
};

class SegmentTree {
private:
    vector<Node*> tr;
    vector<int> nums;

public:
    SegmentTree(vector<int>& nums) {
        int n = nums.size() - 1;
        this->nums = nums;
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 0, n - 1);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    void modify(int u, int l, int r, int k) {
        if (tr[u]->l >= l && tr[u]->r <= r)
            tr[u]->cnt += k;
        else {
            int mid = (tr[u]->l + tr[u]->r) >> 1;
            if (l <= mid) modify(u << 1, l, r, k);
            if (r > mid) modify(u << 1 | 1, l, r, k);
        }
        pushup(u);
    }

    int query() {
        return tr[1]->len;
    }

    void pushup(int u) {
        if (tr[u]->cnt)
            tr[u]->len = nums[tr[u]->r + 1] - nums[tr[u]->l];
        else if (tr[u]->l == tr[u]->r)
            tr[u]->len = 0;
        else
            tr[u]->len = tr[u << 1]->len + tr[u << 1 | 1]->len;
    }
};

class Solution {
public:
    int rectangleArea(vector<vector<int>>& rectangles) {
        int n = rectangles.size();
        vector<vector<int>> segs;
        set<int> ts;
        int mod = 1e9 + 7;
        for (auto& rect : rectangles) {
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
            segs.push_back({x1, y1, y2, 1});
            segs.push_back({x2, y1, y2, -1});
            ts.insert(y1);
            ts.insert(y2);
        }
        unordered_map<int, int> m;
        int idx = 0;
        for (int v : ts) m[v] = idx++;
        sort(segs.begin(), segs.end());
        vector<int> nums(ts.begin(), ts.end());
        SegmentTree* tree = new SegmentTree(nums);
        long long ans = 0;
        for (int i = 0; i < segs.size(); ++i) {
            int x = segs[i][0], y1 = segs[i][1], y2 = segs[i][2], k = segs[i][3];
            if (i > 0) ans += (long long)tree->query() * (x - segs[i - 1][0]);
            tree->modify(1, m[y1], m[y2] - 1, k);
        }
        ans %= mod;
        return (int)ans;
    }
};
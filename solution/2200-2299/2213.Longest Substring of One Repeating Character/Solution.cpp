class Node {
public:
    int l, r;
    int lmx, rmx, mx;

    Node(int l, int r)
        : l(l)
        , r(r)
        , lmx(1)
        , rmx(1)
        , mx(1) {}
};

class SegmentTree {
private:
    string s;
    vector<Node*> tr;

    void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        Node* root = tr[u];
        Node* left = tr[u << 1];
        Node* right = tr[u << 1 | 1];
        root->mx = max(left->mx, right->mx);
        root->lmx = left->lmx;
        root->rmx = right->rmx;
        int a = left->r - left->l + 1;
        int b = right->r - right->l + 1;
        if (s[left->r - 1] == s[right->l - 1]) {
            if (left->lmx == a) {
                root->lmx += right->lmx;
            }
            if (right->rmx == b) {
                root->rmx += left->rmx;
            }
            root->mx = max(root->mx, left->rmx + right->lmx);
        }
    }

public:
    SegmentTree(const string& s)
        : s(s) {
        int n = s.size();
        tr.resize(n * 4);
        build(1, 1, n);
    }

    void modify(int u, int x, char v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            s[x - 1] = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return tr[u]->mx;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int ans = 0;
        if (r <= mid) {
            ans = query(u << 1, l, r);
        } else if (l > mid) {
            ans = max(ans, query(u << 1 | 1, l, r));
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        SegmentTree tree(s);
        int k = queryIndices.size();
        vector<int> ans(k);
        int n = s.size();
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            char v = queryCharacters[i];
            tree.modify(1, x, v);
            ans[i] = tree.query(1, 1, n);
        }
        return ans;
    }
};
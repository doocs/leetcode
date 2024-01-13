class Node {
public:
    int l, r, size, lmx, rmx, mx;
    char lc, rc;
};

class SegmentTree {
private:
    string s;
    vector<Node*> tr;

public:
    SegmentTree(string& s) {
        this->s = s;
        int n = s.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->lmx = tr[u]->rmx = tr[u]->mx = tr[u]->size = 1;
            tr[u]->lc = tr[u]->rc = s[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int x, char v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            tr[u]->lc = tr[u]->rc = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid)
            modify(u << 1, x, v);
        else
            modify(u << 1 | 1, x, v);
        pushup(u);
    }

    Node* query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u];
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (r <= mid) return query(u << 1, l, r);
        if (l > mid) query(u << 1 | 1, l, r);
        Node* ans = new Node();
        Node* left = query(u << 1, l, r);
        Node* right = query(u << 1 | 1, l, r);
        pushup(ans, left, right);
        return ans;
    }

    void pushup(Node* root, Node* left, Node* right) {
        root->lc = left->lc;
        root->rc = right->rc;
        root->size = left->size + right->size;

        root->mx = max(left->mx, right->mx);
        root->lmx = left->lmx;
        root->rmx = right->rmx;

        if (left->rc == right->lc) {
            if (left->lmx == left->size) root->lmx += right->lmx;
            if (right->rmx == right->size) root->rmx += left->rmx;
            root->mx = max(root->mx, left->rmx + right->lmx);
        }
    }

    void pushup(int u) {
        pushup(tr[u], tr[u << 1], tr[u << 1 | 1]);
    }
};

class Solution {
public:
    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        SegmentTree* tree = new SegmentTree(s);
        int k = queryCharacters.size();
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            tree->modify(1, x, queryCharacters[i]);
            ans[i] = tree->query(1, 1, s.size())->mx;
        }
        return ans;
    }
};
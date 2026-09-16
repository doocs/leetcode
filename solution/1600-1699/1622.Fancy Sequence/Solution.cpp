const int MOD = 1e9 + 7;

class Node {
public:
    Node* left = nullptr;
    Node* right = nullptr;
    int l, r, mid;
    long long v = 0, add = 0, mul = 1;

    Node(int l, int r)
        : l(l)
        , r(r)
        , mid((l + r) >> 1) {}
};

class SegmentTree {
public:
    SegmentTree()
        : root(new Node(1, 1e5 + 1)) {}

    void modify(int l, int r, int mul, int add) {
        modify(l, r, mul, add, root);
    }

    int query(int l, int r) {
        return query(l, r, root);
    }

private:
    Node* root;

    void modify(int l, int r, int mul, int add, Node* node) {
        if (l > r) {
            return;
        }
        if (node->l >= l && node->r <= r) {
            apply(node, mul, add);
            return;
        }
        pushdown(node);
        if (l <= node->mid) {
            modify(l, r, mul, add, node->left);
        }
        if (r > node->mid) {
            modify(l, r, mul, add, node->right);
        }
        pushup(node);
    }

    int query(int l, int r, Node* node) {
        if (l > r) {
            return 0;
        }
        if (node->l >= l && node->r <= r) {
            return node->v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node->mid) {
            v = (v + query(l, r, node->left)) % MOD;
        }
        if (r > node->mid) {
            v = (v + query(l, r, node->right)) % MOD;
        }
        return v;
    }

    void apply(Node* node, long long mul, long long add) {
        node->v = (node->v * mul + (node->r - node->l + 1) * add) % MOD;
        node->add = (node->add * mul + add) % MOD;
        node->mul = node->mul * mul % MOD;
    }

    void pushup(Node* node) {
        node->v = (node->left->v + node->right->v) % MOD;
    }

    void pushdown(Node* node) {
        if (!node->left) {
            node->left = new Node(node->l, node->mid);
        }
        if (!node->right) {
            node->right = new Node(node->mid + 1, node->r);
        }
        if (node->add || node->mul != 1) {
            apply(node->left, node->mul, node->add);
            apply(node->right, node->mul, node->add);
            node->add = 0;
            node->mul = 1;
        }
    }
};

class Fancy {
public:
    void append(int val) {
        ++n;
        tree.modify(n, n, 1, val);
    }

    void addAll(int inc) {
        tree.modify(1, n, 1, inc);
    }

    void multAll(int m) {
        tree.modify(1, n, m, 0);
    }

    int getIndex(int idx) {
        return idx >= n ? -1 : tree.query(idx + 1, idx + 1);
    }

private:
    int n = 0;
    SegmentTree tree;
};

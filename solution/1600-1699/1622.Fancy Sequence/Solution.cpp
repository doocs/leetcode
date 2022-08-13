const int MOD = 1e9 + 7;

class Node {
public:
    Node* left;
    Node* right;
    int l;
    int r;
    int mid;
    long long v;
    long long add;
    long long mul;

    Node(int l, int r) {
        this->l = l;
        this->r = r;
        this->mid = (l + r) >> 1;
        this->left = this->right = nullptr;
        v = add = 0;
        mul = 1;
    }
};

class SegmentTree {
private:
    Node* root;

public:
    SegmentTree() {
        root = new Node(1, 1e5 + 1);
    }

    void modifyAdd(int l, int r, int inc) {
        modifyAdd(l, r, inc, root);
    }

    void modifyAdd(int l, int r, int inc, Node* node) {
        if (l > r) return;
        if (node->l >= l && node->r <= r) {
            node->v = (node->v + (node->r - node->l + 1) * inc) % MOD;
            node->add = (node->add + inc) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node->mid) modifyAdd(l, r, inc, node->left);
        if (r > node->mid) modifyAdd(l, r, inc, node->right);
        pushup(node);
    }

    void modifyMul(int l, int r, int m) {
        modifyMul(l, r, m, root);
    }

    void modifyMul(int l, int r, int m, Node* node) {
        if (l > r) return;
        if (node->l >= l && node->r <= r) {
            node->v = (node->v * m) % MOD;
            node->add = (node->add * m) % MOD;
            node->mul = (node->mul * m) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node->mid) modifyMul(l, r, m, node->left);
        if (r > node->mid) modifyMul(l, r, m, node->right);
        pushup(node);
    }

    int query(int l, int r) {
        return query(l, r, root);
    }

    int query(int l, int r, Node* node) {
        if (l > r) return 0;
        if (node->l >= l && node->r <= r) return node->v;
        pushdown(node);
        int v = 0;
        if (l <= node->mid) v = (v + query(l, r, node->left)) % MOD;
        if (r > node->mid) v = (v + query(l, r, node->right)) % MOD;
        return v;
    }

    void pushup(Node* node) {
        node->v = (node->left->v + node->right->v) % MOD;
    }

    void pushdown(Node* node) {
        if (!node->left) node->left = new Node(node->l, node->mid);
        if (!node->right) node->right = new Node(node->mid + 1, node->r);
        if (node->add || node->mul != 1) {
            long add = node->add, mul = node->mul;
            Node* left = node->left;
            Node* right = node->right;
            left->v = (left->v * mul + (left->r - left->l + 1) * add) % MOD;
            right->v = (right->v * mul + (right->r - right->l + 1) * add) % MOD;
            left->add = (left->add * mul + add) % MOD;
            right->add = (right->add * mul + add) % MOD;
            left->mul = (left->mul * mul) % MOD;
            right->mul = (right->mul * mul) % MOD;
            node->add = 0;
            node->mul = 1;
        }
    }
};

class Fancy {
public:
    int n;
    SegmentTree* tree;

    Fancy() {
        n = 0;
        tree = new SegmentTree();
    }

    void append(int val) {
        ++n;
        tree->modifyAdd(n, n, val);
    }

    void addAll(int inc) {
        tree->modifyAdd(1, n, inc);
    }

    void multAll(int m) {
        tree->modifyMul(1, n, m);
    }

    int getIndex(int idx) {
        return idx >= n ? -1 : tree->query(idx + 1, idx + 1);
    }
};

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy* obj = new Fancy();
 * obj->append(val);
 * obj->addAll(inc);
 * obj->multAll(m);
 * int param_4 = obj->getIndex(idx);
 */
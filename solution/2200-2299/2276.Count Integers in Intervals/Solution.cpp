class Node {
public:
    Node(int l, int r)
        : l(l)
        , r(r)
        , mid((l + r) / 2)
        , v(0)
        , add(0)
        , left(nullptr)
        , right(nullptr) {}

    int l, r, mid, v, add;
    Node* left;
    Node* right;
};

class SegmentTree {
public:
    SegmentTree()
        : root(new Node(1, 1000000001)) {}

    void modify(int l, int r, int v, Node* node = nullptr) {
        if (node == nullptr) {
            node = root;
        }
        if (l > r) {
            return;
        }
        if (node->l >= l && node->r <= r) {
            node->v = node->r - node->l + 1;
            node->add = v;
            return;
        }
        pushdown(node);
        if (l <= node->mid) {
            modify(l, r, v, node->left);
        }
        if (r > node->mid) {
            modify(l, r, v, node->right);
        }
        pushup(node);
    }

    int query(int l, int r, Node* node = nullptr) {
        if (node == nullptr) {
            node = root;
        }
        if (l > r) {
            return 0;
        }
        if (node->l >= l && node->r <= r) {
            return node->v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node->mid) {
            v += query(l, r, node->left);
        }
        if (r > node->mid) {
            v += query(l, r, node->right);
        }
        return v;
    }

private:
    Node* root;

    void pushup(Node* node) {
        node->v = node->left->v + node->right->v;
    }

    void pushdown(Node* node) {
        if (node->left == nullptr) {
            node->left = new Node(node->l, node->mid);
        }
        if (node->right == nullptr) {
            node->right = new Node(node->mid + 1, node->r);
        }
        if (node->add != 0) {
            Node* left = node->left;
            Node* right = node->right;
            left->add = node->add;
            right->add = node->add;
            left->v = left->r - left->l + 1;
            right->v = right->r - right->l + 1;
            node->add = 0;
        }
    }
};

class CountIntervals {
public:
    CountIntervals() {}

    void add(int left, int right) {
        tree.modify(left, right, 1);
    }

    int count() {
        return tree.query(1, 1000000000);
    }

private:
    SegmentTree tree;
};

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals* obj = new CountIntervals();
 * obj->add(left,right);
 * int param_2 = obj->count();
 */
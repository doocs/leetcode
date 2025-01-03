class Node {
public:
    int l, r, mid, v, add;
    Node* left;
    Node* right;

    Node(int l, int r)
        : l(l)
        , r(r)
        , mid((l + r) >> 1)
        , v(0)
        , add(0)
        , left(nullptr)
        , right(nullptr) {}
};

class SegmentTree {
public:
    Node* root;

    SegmentTree() {
        root = new Node(1, 1e9 + 1);
    }

    void modify(int l, int r, int v, Node* node = nullptr) {
        if (l > r) {
            return;
        }
        if (node == nullptr) {
            node = root;
        }

        if (node->l >= l && node->r <= r) {
            node->v += v;
            node->add += v;
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
        if (l > r) {
            return 0;
        }
        if (node == nullptr) {
            node = root;
        }

        if (node->l >= l && node->r <= r) {
            return node->v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node->mid) {
            v = max(v, query(l, r, node->left));
        }
        if (r > node->mid) {
            v = max(v, query(l, r, node->right));
        }
        return v;
    }

private:
    void pushup(Node* node) {
        node->v = max(node->left->v, node->right->v);
    }

    void pushdown(Node* node) {
        if (node->left == nullptr) {
            node->left = new Node(node->l, node->mid);
        }
        if (node->right == nullptr) {
            node->right = new Node(node->mid + 1, node->r);
        }
        if (node->add) {
            node->left->v += node->add;
            node->right->v += node->add;
            node->left->add += node->add;
            node->right->add += node->add;
            node->add = 0;
        }
    }
};

class MyCalendarTwo {
public:
    SegmentTree tree;

    MyCalendarTwo() {}

    bool book(int startTime, int endTime) {
        if (tree.query(startTime + 1, endTime) >= 2) {
            return false;
        }
        tree.modify(startTime + 1, endTime, 1);
        return true;
    }
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(startTime,endTime);
 */

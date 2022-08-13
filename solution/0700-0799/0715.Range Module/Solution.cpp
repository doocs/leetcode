template <class T>
class CachedObj {
public:
    void* operator new(size_t s) {
        if (!head) {
            T* a = new T[SIZE];
            for (size_t i = 0; i < SIZE; ++i)
                add(a + i);
        }
        T* p = head;
        head = head->CachedObj<T>::next;
        return p;
    }
    void operator delete(void* p, size_t) {
        if (p) add(static_cast<T*>(p));
    }
    virtual ~CachedObj() { }

protected:
    T* next;

private:
    static T* head;
    static const size_t SIZE;
    static void add(T* p) {
        p->CachedObj<T>::next = head;
        head = p;
    }
};
template <class T>
T* CachedObj<T>::head = 0;
template <class T>
const size_t CachedObj<T>::SIZE = 10000;
class Node : public CachedObj<Node> {
public:
    Node* left;
    Node* right;
    int add;
    bool v;
};

class SegmentTree {
private:
    Node* root;

public:
    SegmentTree() {
        root = new Node();
    }

    void modify(int left, int right, int v) {
        modify(left, right, v, 1, 1e9, root);
    }

    void modify(int left, int right, int v, int l, int r, Node* node) {
        if (l >= left && r <= right) {
            node->v = v == 1;
            node->add = v;
            return;
        }
        pushdown(node);
        int mid = (l + r) >> 1;
        if (left <= mid) modify(left, right, v, l, mid, node->left);
        if (right > mid) modify(left, right, v, mid + 1, r, node->right);
        pushup(node);
    }

    bool query(int left, int right) {
        return query(left, right, 1, 1e9, root);
    }

    bool query(int left, int right, int l, int r, Node* node) {
        if (l >= left && r <= right) return node->v;
        pushdown(node);
        int mid = (l + r) >> 1;
        bool v = true;
        if (left <= mid) v = v && query(left, right, l, mid, node->left);
        if (right > mid) v = v && query(left, right, mid + 1, r, node->right);
        return v;
    }

    void pushup(Node* node) {
        node->v = node->left && node->left->v && node->right && node->right->v;
    }

    void pushdown(Node* node) {
        if (!node->left) node->left = new Node();
        if (!node->right) node->right = new Node();
        if (node->add) {
            node->left->add = node->right->add = node->add;
            node->left->v = node->right->v = node->add == 1;
            node->add = 0;
        }
    }
};

class RangeModule {
public:
    SegmentTree* tree;

    RangeModule() {
        tree = new SegmentTree();
    }

    void addRange(int left, int right) {
        tree->modify(left, right - 1, 1);
    }

    bool queryRange(int left, int right) {
        return tree->query(left, right - 1);
    }

    void removeRange(int left, int right) {
        tree->modify(left, right - 1, -1);
    }
};

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule* obj = new RangeModule();
 * obj->addRange(left,right);
 * bool param_2 = obj->queryRange(left,right);
 * obj->removeRange(left,right);
 */
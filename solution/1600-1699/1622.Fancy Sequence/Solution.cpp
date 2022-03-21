const int MOD = 1e9 + 7;

class SegmentTree {
private:
    int l;
    int r;
    SegmentTree* left;
    SegmentTree* right;
    long long v;
    long long add;
    long long mul;
    
public:
    SegmentTree(int l, int r) {
        this->l = l;
        this->r = r;
        this->left = nullptr;
        this->right = nullptr;
        this->v = 0;
        this->add = 0;
        this->mul = 1;
    }

    void modifyAdd(int l, int r, int inc) {
        if (l > r) return;
        if (this->l >= l && this->r <= r)
        {
            v = (v + (this->r - this->l + 1) * inc) % MOD;
            add = (add + inc) % MOD;
            return;
        }
        pushdown();
        int mid = _mid();
        if (l <= mid) _left()->modifyAdd(l, r, inc);
        if (r > mid) _right()->modifyAdd(l, r, inc);
        pushup();
    }

    void modifyMul(int l, int r, int m) {
        if (l > r) return;
        if (this->l >= l && this->r <= r)
        {
            v = (v * m) % MOD;
            add = (add * m) % MOD;
            mul = (mul * m) % MOD;
            return;
        }
        pushdown();
        int mid = _mid();
        if (l <= mid) _left()->modifyMul(l, r, m);
        if (r > mid) _right()->modifyMul(l, r, m);
        pushup();
    }

    int query(int l, int r) {
        if (l > r) return 0;
        if (this->l >= l && this->r <= r) return v;
        pushdown();
        int mid = _mid();
        int v = 0;
        if (l <= mid) v = (v +  _left()->query(l, r)) % MOD;
        if (r > mid) v = (v + _right()->query(l, r)) % MOD;
        return v;
    }

    int _mid() {
        return (l + r) >> 1;
    }

    SegmentTree* _left() {
        if (!left) left = new SegmentTree(l, _mid());
        return left;
    }

    SegmentTree* _right() {
        if (!right) right = new SegmentTree(_mid() + 1, r);
        return right;
    }

    void pushup() {
        v = (_left()->v + _right()->v) % MOD;
    }

    void pushdown() {
        if (add != 0 || mul != 1)
        {
            _left()->v = (_left()->v * mul + (_left()->r - _left()->l + 1) * add) % MOD;
            _right()->v = (_right()->v * mul + (_right()->r - _right()->l + 1) * add) % MOD;
            _left()->add = (_left()->add * mul + add) % MOD;
            _right()->add = (_right()->add * mul + add) % MOD;
            _left()->mul = (_left()->mul * mul) % MOD;
            _right()->mul = (_right()->mul * mul) % MOD;
            add = 0;
            mul = 1;
        }
    }
};

class Fancy {
public:
    int n;
    SegmentTree* tree;

    Fancy() {
        this-> n = 0;
        tree = new SegmentTree(1, 1e5 + 1);
    }
    
    void append(int val) {
        ++this->n;
        tree->modifyAdd(this->n, this->n, val);
    }
    
    void addAll(int inc) {
        tree->modifyAdd(1, this->n, inc);
    }
    
    void multAll(int m) {
        tree->modifyMul(1, this->n, m);
    }
    
    int getIndex(int idx) {
        return idx >= this-> n ? -1 : tree->query(idx + 1, idx + 1);
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
class Bitset {
public:
    string a, b;
    int cnt = 0;

    Bitset(int size) {
        a = string(size, '0');
        b = string(size, '1');
    }

    void fix(int idx) {
        if (a[idx] == '0') a[idx] = '1', ++cnt;
        b[idx] = '0';
    }

    void unfix(int idx) {
        if (a[idx] == '1') a[idx] = '0', --cnt;
        b[idx] = '1';
    }

    void flip() {
        swap(a, b);
        cnt = a.size() - cnt;
    }

    bool all() {
        return cnt == a.size();
    }

    bool one() {
        return cnt > 0;
    }

    int count() {
        return cnt;
    }

    string toString() {
        return a;
    }
};

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset* obj = new Bitset(size);
 * obj->fix(idx);
 * obj->unfix(idx);
 * obj->flip();
 * bool param_4 = obj->all();
 * bool param_5 = obj->one();
 * int param_6 = obj->count();
 * string param_7 = obj->toString();
 */
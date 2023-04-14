class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class MRUQueue {
public:
    MRUQueue(int n) {
        q.resize(n + 1);
        iota(q.begin() + 1, q.end(), 1);
        tree = new BinaryIndexedTree(n + 2010);
    }

    int fetch(int k) {
        int l = 1, r = q.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mid - tree->query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int x = q[l];
        q.push_back(x);
        tree->update(l, 1);
        return x;
    }

private:
    vector<int> q;
    BinaryIndexedTree* tree;
};

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue* obj = new MRUQueue(n);
 * int param_1 = obj->fetch(k);
 */
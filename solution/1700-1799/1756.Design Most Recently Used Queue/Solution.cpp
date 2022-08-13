class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class MRUQueue {
public:
    int n;
    vector<int> data;
    BinaryIndexedTree* tree;

    MRUQueue(int n) {
        this->n = n;
        data.resize(n + 1);
        for (int i = 1; i <= n; ++i) data[i] = i;
        tree = new BinaryIndexedTree(n + 2010);
    }

    int fetch(int k) {
        int left = 1, right = data.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid - tree->query(mid) >= k)
                right = mid;
            else
                left = mid + 1;
        }
        data.push_back(data[left]);
        tree->update(left, 1);
        return data[left];
    }
};

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue* obj = new MRUQueue(n);
 * int param_1 = obj->fetch(k);
 */
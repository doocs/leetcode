class Allocator {
public:
    Allocator(int n) {
        m = vector<int>(n);
    }

    int allocate(int size, int mID) {
        int cnt = 0;
        for (int i = 0; i < m.size(); ++i) {
            if (m[i]) {
                cnt = 0;
            } else if (++cnt == size) {
                fill(i - size + 1, i + 1, mID);
                return i - size + 1;
            }
        }
        return -1;
    }

    int free(int mID) {
        int ans = 0;
        for (int i = 0; i < m.size(); ++i) {
            if (m[i] == mID) {
                m[i] = 0;
                ++ans;
            }
        }
        return ans;
    }

private:
    vector<int> m;

    void fill(int from, int to, int val) {
        for (int i = from; i < to; ++i) {
            m[i] = val;
        }
    }
};

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator* obj = new Allocator(n);
 * int param_1 = obj->allocate(size,mID);
 * int param_2 = obj->free(mID);
 */
class Allocator {
public:
    vector<int> m;

    Allocator(int n) {
        m = vector<int>(n, 0);
    }

    int allocate(int size, int mID) {
        int cnt = 0;
        for (int i = 0; i < m.size(); ++i) {
            if (m[i] > 0) {
                cnt = 0;
            } else if (++cnt == size) {
                fill(m.begin() + i - size + 1, m.begin() + i + 1, mID);
                return i - size + 1;
            }
        }
        return -1;
    }

    int freeMemory(int mID) {
        int ans = 0;
        for (int i = 0; i < m.size(); ++i) {
            if (m[i] == mID) {
                m[i] = 0;
                ++ans;
            }
        }
        return ans;
    }
};

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator* obj = new Allocator(n);
 * int param_1 = obj->allocate(size,mID);
 * int param_2 = obj->freeMemory(mID);
 */

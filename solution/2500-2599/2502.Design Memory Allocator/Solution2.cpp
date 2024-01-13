class Allocator {
public:
    Allocator(int n) {
        tm[-1] = -1;
        tm[n] = n;
    }

    int allocate(int size, int mID) {
        int s = -1;
        for (auto& [v, c] : tm) {
            if (s != -1) {
                int e = v - 1;
                if (e - s + 1 >= size) {
                    tm[s] = s + size - 1;
                    d[mID].emplace_back(s);
                    return s;
                }
            }
            s = c + 1;
        }
        return -1;
    }

    int free(int mID) {
        int ans = 0;
        for (int& s : d[mID]) {
            int e = tm[s];
            tm.erase(s);
            ans += e - s + 1;
        }
        d.erase(mID);
        return ans;
    }

private:
    map<int, int> tm;
    unordered_map<int, vector<int>> d;
};

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator* obj = new Allocator(n);
 * int param_1 = obj->allocate(size,mID);
 * int param_2 = obj->free(mID);
 */
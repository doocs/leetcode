class DetectSquares {
public:
    unordered_map<int, unordered_map<int, int>> mp;

    DetectSquares() {
    }

    void add(vector<int> point) {
        int x = point[0], y = point[1];
        ++mp[x][y];
    }

    int count(vector<int> point) {
        int x = point[0], y = point[1];
        int ans = 0;
        if (!mp.count(x)) return ans;
        auto xcnt = mp[x];
        for (auto e : mp) {
            int x1 = e.first;
            auto counter = e.second;
            if (x1 != x) {
                int d = x1 - x;
                ans += xcnt[y + d] * counter[y] * counter[y + d];
                ans += xcnt[y - d] * counter[y] * counter[y - d];
            }
        }
        return ans;
    }
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
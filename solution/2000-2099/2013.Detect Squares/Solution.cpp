class DetectSquares {
public:
    DetectSquares() {
    }

    void add(vector<int> point) {
        int x = point[0], y = point[1];
        ++cnt[x][y];
    }

    int count(vector<int> point) {
        int x1 = point[0], y1 = point[1];
        if (!cnt.count(x1)) {
            return 0;
        }
        int ans = 0;
        for (auto& [x2, cnt2] : cnt) {
            if (x2 != x1) {
                int d = x2 - x1;
                auto& cnt1 = cnt[x1];
                ans += cnt2[y1] * cnt1[y1 + d] * cnt2[y1 + d];
                ans += cnt2[y1] * cnt1[y1 - d] * cnt2[y1 - d];
            }
        }
        return ans;
    }

private:
    unordered_map<int, unordered_map<int, int>> cnt;
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
class Solution {
public:
    vector<int> s;
    vector<vector<int>> rects;

    Solution(vector<vector<int>>& rects) {
        int n = rects.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
        this->rects = rects;
        srand(time(nullptr));
    }

    vector<int> pick() {
        int n = rects.size();
        int v = 1 + rand() % s[n];
        int idx = lower_bound(s.begin(), s.end(), v) - s.begin();
        auto& rect = rects[idx - 1];
        int x = rect[0] + rand() % (rect[2] - rect[0] + 1);
        int y = rect[1] + rand() % (rect[3] - rect[1] + 1);
        return {x, y};
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(rects);
 * vector<int> param_1 = obj->pick();
 */
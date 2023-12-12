class Solution {
public:
    int digArtifacts(int n, vector<vector<int>>& artifacts, vector<vector<int>>& dig) {
        unordered_set<int> s;
        for (auto& p : dig) {
            s.insert(p[0] * n + p[1]);
        }
        auto check = [&](vector<int>& a) {
            int x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
            for (int x = x1; x <= x2; ++x) {
                for (int y = y1; y <= y2; ++y) {
                    if (!s.count(x * n + y)) {
                        return 0;
                    }
                }
            }
            return 1;
        };
        int ans = 0;
        for (auto& a : artifacts) {
            ans += check(a);
        }
        return ans;
    }
};
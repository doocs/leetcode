class Solution {
public:
    int digArtifacts(int n, vector<vector<int>>& artifacts, vector<vector<int>>& dig) {
        unordered_set<int> s;
        for (auto& d : dig) s.insert(d[0] * n + d[1]);
        int ans = 0;
        for (auto& a : artifacts) ans += check(a, s, n);
        return ans;
    }

    bool check(vector<int>& a, unordered_set<int>& s, int n) {
        int r1 = a[0], c1 = a[1], r2 = a[2], c2 = a[3];
        for (int i = r1; i <= r2; ++i) {
            for (int j = c1; j <= c2; ++j) {
                if (!s.count(i * n + j)) {
                    return false;
                }
            }
        }
        return true;
    }
};
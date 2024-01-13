class Solution {
public:
    int minWastedSpace(vector<int>& packages, vector<vector<int>>& boxes) {
        int n = packages.size(), m = boxes.size();
        sort(packages.begin(), packages.end());
        const int mod = 1e9 + 7;
        const long long inf = 1LL << 62;
        long long ans = inf;
        for (auto& box : boxes) {
            sort(box.begin(), box.end());
            if (packages.back() > box.back()) {
                continue;
            }
            int i = 0;
            long long s = 0;
            for (auto& b : box) {
                int j = upper_bound(packages.begin() + i, packages.end(), b) - packages.begin();
                s += 1LL * (j - i) * b;
                i = j;
            }
            ans = min(ans, s);
        }
        return ans == inf ? -1 : (ans - accumulate(packages.begin(), packages.end(), 0LL)) % mod;
    }
};
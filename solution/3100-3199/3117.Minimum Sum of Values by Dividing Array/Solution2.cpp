class Solution {
public:
    int minimumValueSum(vector<int>& nums, vector<int>& andValues) {
        int n = nums.size(), m = andValues.size();
        const int stride = 100001;
        unordered_map<int, int> f;
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            unordered_map<int, int> g;
            for (auto& [key, cost] : f) {
                int j = key / stride;
                int a = key % stride - 1;
                if (n - i < m - j) {
                    continue;
                }
                int na = a & nums[i];
                if (na < andValues[j]) {
                    continue;
                }
                int nk = j * stride + na + 1;
                if (!g.contains(nk) || g[nk] > cost) {
                    g[nk] = cost;
                }
                if (na == andValues[j]) {
                    int t = cost + nums[i];
                    if (j + 1 == m) {
                        if (i == n - 1) {
                            int done = m * stride;
                            if (!g.contains(done) || g[done] > t) {
                                g[done] = t;
                            }
                        }
                    } else {
                        int nk2 = (j + 1) * stride;
                        if (!g.contains(nk2) || g[nk2] > t) {
                            g[nk2] = t;
                        }
                    }
                }
            }
            f.swap(g);
        }
        int done = m * stride;
        return f.contains(done) ? f[done] : -1;
    }
};

class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        vector<int> masks(n);
        for (int i = 0; i < n; ++i) {
            masks[i] = 1 << (s[i] - 'a');
        }
        vector<unordered_set<int>> reach(n + 1);
        vector<unordered_map<int, int>> f(n + 1);
        reach[0].insert(1);
        for (int i = 0; i < n; ++i) {
            int v = masks[i];
            for (int key : reach[i]) {
                int cur = key >> 1, t = key & 1;
                int nxt = cur | v;
                if (__builtin_popcount(nxt) > k) {
                    reach[i + 1].insert((v << 1) | t);
                } else {
                    reach[i + 1].insert((nxt << 1) | t);
                }
                if (t) {
                    for (int j = 0; j < 26; ++j) {
                        int bit = 1 << j;
                        nxt = cur | bit;
                        if (__builtin_popcount(nxt) > k) {
                            reach[i + 1].insert(bit << 1);
                        } else {
                            reach[i + 1].insert(nxt << 1);
                        }
                    }
                }
            }
        }
        auto get = [&](int i, int key) -> int {
            if (i == n) {
                return 1;
            }
            return f[i].at(key);
        };
        for (int i = n - 1; i >= 0; --i) {
            int v = masks[i];
            for (int key : reach[i]) {
                int cur = key >> 1, t = key & 1;
                int nxt = cur | v;
                int ans = __builtin_popcount(nxt) > k ? get(i + 1, (v << 1) | t) + 1
                                                      : get(i + 1, (nxt << 1) | t);
                if (t) {
                    for (int j = 0; j < 26; ++j) {
                        int bit = 1 << j;
                        nxt = cur | bit;
                        if (__builtin_popcount(nxt) > k) {
                            ans = max(ans, get(i + 1, bit << 1) + 1);
                        } else {
                            ans = max(ans, get(i + 1, nxt << 1));
                        }
                    }
                }
                f[i][key] = ans;
            }
        }
        return f[0].at(1);
    }
};

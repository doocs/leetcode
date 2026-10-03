class Hashing {
private:
    vector<long long> p;
    vector<long long> h;
    long long mod;

public:
    Hashing(string word, long long base, int mod) {
        int n = word.size();
        p.resize(n + 1);
        h.resize(n + 1);
        p[0] = 1;
        this->mod = mod;
        for (int i = 1; i <= n; i++) {
            p[i] = (p[i - 1] * base) % mod;
            h[i] = (h[i - 1] * base + word[i - 1] - 'a') % mod;
        }
    }

    long long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    vector<bool> findAnswer(vector<int>& parent, string s) {
        int n = s.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        string dfsStr;
        vector<pair<int, int>> pos(n);
        vector<array<int, 2>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                pos[i].first = (int) dfsStr.size() + 1;
                stk.push_back({i, 1});
                for (int t = (int) g[i].size() - 1; t >= 0; --t) {
                    stk.push_back({g[i][t], 0});
                }
            } else {
                dfsStr.push_back(s[i]);
                pos[i].second = (int) dfsStr.size();
            }
        }

        const int base = 13331;
        const int mod = 998244353;
        Hashing h1(dfsStr, base, mod);
        reverse(dfsStr.begin(), dfsStr.end());
        Hashing h2(dfsStr, base, mod);
        vector<bool> ans(n);
        for (int i = 0; i < n; ++i) {
            auto [l, r] = pos[i];
            int k = r - l + 1;
            long long v1 = h1.query(l, l + k / 2 - 1);
            long long v2 = h2.query(n - r + 1, n - r + 1 + k / 2 - 1);
            ans[i] = v1 == v2;
        }
        return ans;
    }
};

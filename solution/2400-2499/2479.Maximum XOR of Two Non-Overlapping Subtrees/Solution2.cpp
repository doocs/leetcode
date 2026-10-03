using ll = long long;

class Trie {
public:
    vector<Trie*> children;
    string v;
    Trie()
        : children(2) {}

    void insert(ll x) {
        Trie* node = this;
        for (int i = 47; ~i; --i) {
            int v = (x >> i) & 1;
            if (!node->children[v]) node->children[v] = new Trie();
            node = node->children[v];
        }
    }

    ll search(ll x) {
        Trie* node = this;
        ll res = 0;
        for (int i = 47; ~i; --i) {
            if (!node) return res;
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                res = res << 1 | 1;
                node = node->children[v ^ 1];
            } else {
                res <<= 1;
                node = node->children[v];
            }
        }
        return res;
    }
};

class Solution {
public:
    long long maxXor(int n, vector<vector<int>>& edges, vector<int>& values) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        vector<ll> s(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int k = (int) g[i].size() - 1; k >= 0; --k) {
                    int j = g[i][k];
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                ll t = values[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        t += s[j];
                    }
                }
                s[i] = t;
            }
        }
        Trie tree;
        ll ans = 0;
        stk.push_back({0, -1, 0});
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                ans = max(ans, tree.search(s[i]));
                stk.push_back({i, fa, 1});
                for (int k = (int) g[i].size() - 1; k >= 0; --k) {
                    int j = g[i][k];
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                tree.insert(s[i]);
            }
        }
        return ans;
    }
};

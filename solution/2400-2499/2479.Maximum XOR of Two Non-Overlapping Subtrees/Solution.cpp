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
        function<ll(int, int)> dfs1 = [&](int i, int fa) -> ll {
            ll t = values[i];
            for (int j : g[i]) {
                if (j != fa) t += dfs1(j, i);
            }
            s[i] = t;
            return t;
        };
        dfs1(0, -1);
        Trie tree;
        ll ans = 0;
        function<void(int, int)> dfs2 = [&](int i, int fa) {
            ans = max(ans, tree.search(s[i]));
            for (int j : g[i]) {
                if (j != fa) {
                    dfs2(j, i);
                }
            }
            tree.insert(s[i]);
        };
        dfs2(0, -1);
        return ans;
    }
};
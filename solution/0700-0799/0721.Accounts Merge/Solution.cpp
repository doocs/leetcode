class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        int n = accounts.size();
        UnionFind uf(n);
        unordered_map<string, int> d;
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < accounts[i].size(); ++j) {
                const string& email = accounts[i][j];
                if (d.find(email) != d.end()) {
                    uf.unite(i, d[email]);
                } else {
                    d[email] = i;
                }
            }
        }
        unordered_map<int, set<string>> g;
        for (int i = 0; i < n; ++i) {
            int root = uf.find(i);
            g[root].insert(accounts[i].begin() + 1, accounts[i].end());
        }
        vector<vector<string>> ans;
        for (const auto& [root, s] : g) {
            vector<string> emails(s.begin(), s.end());
            emails.insert(emails.begin(), accounts[root][0]);
            ans.push_back(emails);
        }
        return ans;
    }
};
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
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
    vector<string> generateSentences(vector<vector<string>>& synonyms, string text) {
        unordered_set<string> ss;
        for (auto& pairs : synonyms) {
            ss.insert(pairs[0]);
            ss.insert(pairs[1]);
        }
        vector<string> words{ss.begin(), ss.end()};
        unordered_map<string, int> d;
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            d[words[i]] = i;
        }
        UnionFind uf(n);
        for (auto& pairs : synonyms) {
            uf.unite(d[pairs[0]], d[pairs[1]]);
        }
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            g[uf.find(i)].push_back(i);
        }
        for (int i = 0; i < n; ++i) {
            sort(g[i].begin(), g[i].end(), [&](int a, int b) {
                return words[a] < words[b];
            });
        }
        vector<string> sentence;
        string s;
        istringstream iss(text);
        while (iss >> s) {
            sentence.emplace_back(s);
        }
        vector<string> ans;
        vector<string> t;
        function<void(int)> dfs = [&](int i) {
            if (i >= sentence.size()) {
                string s;
                for (int j = 0; j < t.size(); ++j) {
                    if (j) {
                        s += " ";
                    }
                    s += t[j];
                }
                ans.emplace_back(s);
                return;
            }
            if (!d.count(sentence[i])) {
                t.emplace_back(sentence[i]);
                dfs(i + 1);
                t.pop_back();
            } else {
                for (int j : g[uf.find(d[sentence[i]])]) {
                    t.emplace_back(words[j]);
                    dfs(i + 1);
                    t.pop_back();
                }
            }
        };
        dfs(0);
        return ans;
    }
};
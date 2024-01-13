class Trie {
public:
    vector<Trie*> children;
    bool is_end;

    Trie() {
        children = vector<Trie*>(26, nullptr);
        is_end = false;
    }

    void insert(const string& word) {
        Trie* cur = this;
        for (char c : word) {
            c -= 'a';
            if (cur->children[c] == nullptr) {
                cur->children[c] = new Trie;
            }
            cur = cur->children[c];
        }
        cur->is_end = true;
    }
};

class Solution {
public:
    vector<string> maxRectangle(vector<string>& words) {
        unordered_map<int, vector<string>> d;
        int maxL = 0, maxS = 0;
        vector<string> ans;
        vector<string> t;
        Trie* trie = new Trie();
        for (auto& w : words) {
            maxL = max(maxL, (int) w.size());
            d[w.size()].emplace_back(w);
            trie->insert(w);
        }
        auto check = [&](vector<string>& mat) {
            int m = mat.size(), n = mat[0].size();
            int ans = 1;
            for (int j = 0; j < n; ++j) {
                Trie* node = trie;
                for (int i = 0; i < m; ++i) {
                    int idx = mat[i][j] - 'a';
                    if (!node->children[idx]) {
                        return 0;
                    }
                    node = node->children[idx];
                }
                if (!node->is_end) {
                    ans = 2;
                }
            }
            return ans;
        };

        function<void(vector<string>&)> dfs = [&](vector<string>& ws) {
            if (ws[0].size() * maxL <= maxS || t.size() >= maxL) {
                return;
            }
            for (auto& w : ws) {
                t.emplace_back(w);
                int st = check(t);
                if (st == 0) {
                    t.pop_back();
                    continue;
                }
                if (st == 1 && maxS < t.size() * t[0].size()) {
                    maxS = t.size() * t[0].size();
                    ans = t;
                }
                dfs(ws);
                t.pop_back();
            }
        };
        for (auto& [_, ws] : d) {
            t.clear();
            dfs(ws);
        }
        return ans;
    }
};
const int inf = 1 << 29;

class Trie {
public:
    Trie* children[26]{};
    int cost = inf;

    void insert(string& word, int cost) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->cost = min(node->cost, cost);
    }
};

class Solution {
public:
    int minimumCost(string target, vector<string>& words, vector<int>& costs) {
        Trie* trie = new Trie();
        for (int i = 0; i < words.size(); ++i) {
            trie->insert(words[i], costs[i]);
        }
        int n = target.length();
        int f[n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            f[i] = inf;
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int idx = target[j] - 'a';
                if (!node->children[idx]) {
                    return f[i];
                }
                node = node->children[idx];
                f[i] = min(f[i], node->cost + dfs(dfs, j + 1));
            }
            return f[i];
        };
        int ans = dfs(dfs, 0);
        return ans < inf ? ans : -1;
    }
};

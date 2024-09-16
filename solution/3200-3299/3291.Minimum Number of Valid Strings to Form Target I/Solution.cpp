class Trie {
public:
    Trie* children[26]{};

    void insert(string& word) {
        Trie* node = this;
        for (char& c : word) {
            int i = c - 'a';
            if (!node->children[i]) {
                node->children[i] = new Trie();
            }
            node = node->children[i];
        }
    }
};

class Solution {
public:
    int minValidStrings(vector<string>& words, string target) {
        int n = target.size();
        Trie* trie = new Trie();
        for (auto& w : words) {
            trie->insert(w);
        }
        const int inf = 1 << 30;
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = inf;
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int k = target[j] - 'a';
                if (!node->children[k]) {
                    break;
                }
                node = node->children[k];
                f[i] = min(f[i], 1 + dfs(dfs, j + 1));
            }
            return f[i];
        };
        int ans = dfs(dfs, 0);
        return ans < inf ? ans : -1;
    }
};

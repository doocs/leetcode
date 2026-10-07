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
        vector<int> f(n + 1, inf);
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int k = target[j] - 'a';
                if (!node->children[k]) {
                    break;
                }
                node = node->children[k];
                f[i] = min(f[i], 1 + f[j + 1]);
            }
        }
        return f[0] < inf ? f[0] : -1;
    }
};

class Trie {
public:
    vector<Trie*> children;
    bool isEnd = false;

    Trie() {
        children.resize(26);
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }
};

class Solution {
public:
    vector<vector<int>> indexPairs(string text, vector<string>& words) {
        Trie* trie = new Trie();
        for (auto w : words) trie->insert(w);
        int n = text.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int idx = text[j] - 'a';
                if (!node->children[idx]) break;
                node = node->children[idx];
                if (node->isEnd) ans.push_back({i, j});
            }
        }
        return ans;
    }
};
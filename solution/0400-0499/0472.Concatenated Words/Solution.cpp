class Trie {
public:
    vector<Trie*> children;
    bool isEnd;
    Trie()
        : children(26)
        , isEnd(false) { }

    void insert(string w) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }
};

class Solution {
public:
    Trie* trie = new Trie();

    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        sort(words.begin(), words.end(), [&](const string& a, const string& b) {
            return a.size() < b.size();
        });
        vector<string> ans;
        for (auto& w : words) {
            if (dfs(w))
                ans.push_back(w);
            else
                trie->insert(w);
        }
        return ans;
    }

    bool dfs(string w) {
        if (w == "") return true;
        Trie* node = trie;
        for (int i = 0; i < w.size(); ++i) {
            int idx = w[i] - 'a';
            if (!node->children[idx]) return false;
            node = node->children[idx];
            if (node->isEnd && dfs(w.substr(i + 1))) return true;
        }
        return false;
    }
};
class Trie {
private:
    vector<Trie*> children;
    bool isEnd;

public:
    Trie()
        : children(26)
        , isEnd(false) { }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }

    bool search(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            node = node->children[c];
            if (!node->isEnd) return false;
        }
        return true;
    }
};

class Solution {
public:
    string longestWord(vector<string>& words) {
        Trie* trie = new Trie();
        for (auto w : words) trie->insert(w);
        string ans = "";
        for (auto w : words) {
            if (ans != "" && (ans.size() > w.size() || (ans.size() == w.size() && ans < w))) continue;
            if (trie->search(w)) ans = w;
        }
        return ans;
    }
};
class Trie {
public:
    Trie* children[26] = {nullptr};
    bool isEnd = false;

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr || !node->children[idx]->isEnd) {
                return false;
            }
            node = node->children[idx];
        }
        return true;
    }
};

class Solution {
public:
    string longestWord(vector<string>& words) {
        Trie trie;
        for (const string& w : words) {
            trie.insert(w);
        }

        string ans = "";
        for (const string& w : words) {
            if (trie.search(w) && (ans.length() < w.length() || (ans.length() == w.length() && w < ans))) {
                ans = w;
            }
        }
        return ans;
    }
};

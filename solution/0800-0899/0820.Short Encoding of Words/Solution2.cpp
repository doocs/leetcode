class Trie {
public:
    vector<Trie*> children;
    Trie()
        : children(26) {}

    int insert(string w) {
        Trie* node = this;
        bool pref = true;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                pref = false;
                node->children[c] = new Trie();
            }
            node = node->children[c];
        }
        return pref ? 0 : w.size() + 1;
    }
};

class Solution {
public:
    int minimumLengthEncoding(vector<string>& words) {
        sort(words.begin(), words.end(), [](string& a, string& b) { return a.size() > b.size(); });
        Trie* trie = new Trie();
        int ans = 0;
        for (auto& w : words) {
            reverse(w.begin(), w.end());
            ans += trie->insert(w);
        }
        return ans;
    }
};
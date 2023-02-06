class Trie {
private:
    vector<Trie*> children;
    int cnt;

public:
    Trie()
        : children(26)
        , cnt(0) {}

    void insert(string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
            ++node->cnt;
        }
    }

    int search(string& w) {
        Trie* node = this;
        int ans = 0;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) return ans;
            node = node->children[idx];
            ans += node->cnt;
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> sumPrefixScores(vector<string>& words) {
        Trie* trie = new Trie();
        for (auto& w : words) {
            trie->insert(w);
        }
        vector<int> ans;
        for (auto& w : words) {
            ans.push_back(trie->search(w));
        }
        return ans;
    }
};
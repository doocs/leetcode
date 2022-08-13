class Trie {
public:
    vector<Trie*> children;
    string v;
    Trie()
        : children(2) { }

    void insert(int x) {
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (!node->children[v]) node->children[v] = new Trie();
            node = node->children[v];
        }
    }

    int search(int x) {
        Trie* node = this;
        int res = 0;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                res = res << 1 | 1;
                node = node->children[v ^ 1];
            } else {
                res <<= 1;
                node = node->children[v];
            }
        }
        return res;
    }
};

class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        Trie* trie = new Trie();
        int ans = 0;
        for (int v : nums) {
            trie->insert(v);
            ans = max(ans, trie->search(v));
        }
        return ans;
    }
};
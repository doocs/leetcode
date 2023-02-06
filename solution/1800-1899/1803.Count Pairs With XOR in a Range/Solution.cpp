class Trie {
public:
    Trie()
        : children(2)
        , cnt(0) {}

    void insert(int x) {
        Trie* node = this;
        for (int i = 15; ~i; --i) {
            int v = x >> i & 1;
            if (!node->children[v]) {
                node->children[v] = new Trie();
            }
            node = node->children[v];
            ++node->cnt;
        }
    }

    int search(int x, int limit) {
        Trie* node = this;
        int ans = 0;
        for (int i = 15; ~i && node; --i) {
            int v = x >> i & 1;
            if (limit >> i & 1) {
                if (node->children[v]) {
                    ans += node->children[v]->cnt;
                }
                node = node->children[v ^ 1];
            } else {
                node = node->children[v];
            }
        }
        return ans;
    }

private:
    vector<Trie*> children;
    int cnt;
};

class Solution {
public:
    int countPairs(vector<int>& nums, int low, int high) {
        Trie* tree = new Trie();
        int ans = 0;
        for (int& x : nums) {
            ans += tree->search(x, high + 1) - tree->search(x, low);
            tree->insert(x);
        }
        return ans;
    }
};
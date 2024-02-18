class Node {
public:
    unordered_map<int, Node*> children;
    int cnt;

    Node()
        : cnt(0) {}
};

class Solution {
public:
    long long countPrefixSuffixPairs(vector<string>& words) {
        long long ans = 0;
        Node* trie = new Node();
        for (const string& s : words) {
            Node* node = trie;
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                int p = s[i] * 32 + s[m - i - 1];
                if (node->children.find(p) == node->children.end()) {
                    node->children[p] = new Node();
                }
                node = node->children[p];
                ans += node->cnt;
            }
            ++node->cnt;
        }
        return ans;
    }
};
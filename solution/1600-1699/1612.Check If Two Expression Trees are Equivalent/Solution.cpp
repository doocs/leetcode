/**
 * Definition for a binary tree node.
 * struct Node {
 *     char val;
 *     Node *left;
 *     Node *right;
 *     Node() : val(' '), left(nullptr), right(nullptr) {}
 *     Node(char x) : val(x), left(nullptr), right(nullptr) {}
 *     Node(char x, Node *left, Node *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool checkEquivalence(Node* root1, Node* root2) {
        int cnt[26]{};
        function<void(Node*, int)> dfs = [&](Node* root, int v) {
            if (!root) {
                return;
            }
            if (root->val != '+') {
                cnt[root->val - 'a'] += v;
            }
            dfs(root->left, v);
            dfs(root->right, v);
        };
        dfs(root1, 1);
        dfs(root2, -1);
        for (int& x : cnt) {
            if (x) {
                return false;
            }
        }
        return true;
    }
};
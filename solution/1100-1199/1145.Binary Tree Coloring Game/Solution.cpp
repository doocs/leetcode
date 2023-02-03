/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool btreeGameWinningMove(TreeNode* root, int n, int x) {
        auto node = dfs(root, x);
        int l = count(node->left), r = count(node->right);
        return max({l, r, n - l - r - 1}) > n / 2;
    }

    TreeNode* dfs(TreeNode* root, int x) {
        if (!root || root->val == x) {
            return root;
        }
        auto node = dfs(root->left, x);
        return node ? node : dfs(root->right, x);
    }

    int count(TreeNode* root) {
        if (!root) {
            return 0;
        }
        return 1 + count(root->left) + count(root->right);
    }
};
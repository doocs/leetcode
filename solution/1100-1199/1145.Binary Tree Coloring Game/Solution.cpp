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
        TreeNode* node = dfs(root, x);
        int l = count(node->left);
        int r = count(node->right);
        int m = max(max(l, r), n - l - r - 1);
        return m > n - m;
    }

    int count(TreeNode* root) {
        if (!root) return 0;
        return 1 + count(root->left) + count(root->right);
    }

    TreeNode* dfs(TreeNode* root, int x) {
        if (!root || root->val == x) return root;
        auto l = dfs(root->left, x);
        return l ? l : dfs(root->right, x);
    }
};
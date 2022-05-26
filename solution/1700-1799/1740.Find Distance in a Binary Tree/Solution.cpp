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
    int findDistance(TreeNode* root, int p, int q) {
        TreeNode* g = lca(root, p, q);
        return dfs(g, p) + dfs(g, q);
    }

    TreeNode* lca(TreeNode* root, int p, int q) {
        if (!root || root->val == p || root->val == q) return root;
        TreeNode* left = lca(root->left, p, q);
        TreeNode* right = lca(root->right, p, q);
        if (!left) return right;
        if (!right) return left;
        return root;
    }

    int dfs(TreeNode* root, int v) {
        if (!root) return -1;
        if (root->val == v) return 0;
        int left = dfs(root->left, v);
        int right = dfs(root->right, v);
        if (left == -1 && right == -1) return -1;
        return 1 + max(left, right);
    }
};
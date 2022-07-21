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
    bool evaluateTree(TreeNode* root) {
        return dfs(root);
    }

    bool dfs(TreeNode* root) {
        if (!root->left && !root->right) return root->val;
        bool l = dfs(root->left), r = dfs(root->right);
        if (root->val == 2) return l || r;
        return l && r;
    }
};
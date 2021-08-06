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
    int val;

    bool isUnivalTree(TreeNode* root) {
        val = root->val;
        return dfs(root);
    }

    bool dfs(TreeNode* root) {
        if (root == nullptr) return true;
        if (root->val != val) return false;
        return dfs(root->left) && dfs(root->right);
    }
};
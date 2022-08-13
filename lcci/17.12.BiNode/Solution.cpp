/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* prev;

    TreeNode* convertBiNode(TreeNode* root) {
        TreeNode* dummy = new TreeNode(0, nullptr, root);
        prev = dummy;
        dfs(root);
        return dummy->right;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        prev->right = root;
        root->left = nullptr;
        prev = root;
        dfs(root->right);
    }
};
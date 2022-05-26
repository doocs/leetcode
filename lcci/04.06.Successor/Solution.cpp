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
    TreeNode* p;
    TreeNode* ans;

    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        this->p = p;
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        if (prev == p) ans = root;
        prev = root;
        dfs(root->right);
    }
};
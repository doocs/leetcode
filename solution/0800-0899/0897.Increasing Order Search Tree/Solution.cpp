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
    TreeNode* prev;

    TreeNode* increasingBST(TreeNode* root) {
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
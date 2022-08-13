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
    TreeNode* removeLeafNodes(TreeNode* root, int target) {
        TreeNode* p = new TreeNode(0, root, nullptr);
        dfs(root, p, target);
        return p->left;
    }

    void dfs(TreeNode* root, TreeNode* prev, int target) {
        if (!root) return;
        dfs(root->left, root, target);
        dfs(root->right, root, target);
        if (!root->left && !root->right && root->val == target) {
            if (prev->left == root)
                prev->left = nullptr;
            else
                prev->right = nullptr;
        }
    }
};
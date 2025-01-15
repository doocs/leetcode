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
    bool isUnivalTree(TreeNode* root) {
        int x = root->val;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> bool {
            if (!root) {
                return true;
            }
            return root->val == x && dfs(root->left) && dfs(root->right);
        };
        return dfs(root);
    }
};

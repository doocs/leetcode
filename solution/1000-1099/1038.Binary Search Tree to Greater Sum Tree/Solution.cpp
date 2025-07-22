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
    TreeNode* bstToGst(TreeNode* root) {
        int s = 0;
        auto dfs = [&](this auto&& dfs, TreeNode* root) {
            if (!root) {
                return;
            }
            dfs(root->right);
            s += root->val;
            root->val = s;
            dfs(root->left);
        };
        dfs(root);
        return root;
    }
};
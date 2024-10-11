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
    vector<int> getLonelyNodes(TreeNode* root) {
        vector<int> ans;
        auto dfs = [&](auto&& dfs, TreeNode* root) {
            if (!root || (root->left == root->right)) {
                return;
            }
            if (!root->left) {
                ans.push_back(root->right->val);
            }
            if (!root->right) {
                ans.push_back(root->left->val);
            }
            dfs(dfs, root->left);
            dfs(dfs, root->right);
        };
        dfs(dfs, root);
        return ans;
    }
};

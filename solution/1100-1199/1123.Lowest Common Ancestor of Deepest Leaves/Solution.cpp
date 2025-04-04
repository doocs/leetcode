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
    TreeNode* lcaDeepestLeaves(TreeNode* root) {
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> pair<TreeNode*, int> {
            if (!root) {
                return {nullptr, 0};
            }
            auto [l, d1] = dfs(root->left);
            auto [r, d2] = dfs(root->right);
            if (d1 > d2) {
                return {l, d1 + 1};
            }
            if (d1 < d2) {
                return {r, d2 + 1};
            }
            return {root, d1 + 1};
        };
        return dfs(root).first;
    }
};
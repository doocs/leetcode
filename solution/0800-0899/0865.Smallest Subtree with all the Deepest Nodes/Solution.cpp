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
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        using pti = pair<TreeNode*, int>;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> pti {
            if (!root) {
                return {nullptr, 0};
            }
            auto [l, ld] = dfs(root->left);
            auto [r, rd] = dfs(root->right);
            if (ld > rd) {
                return {l, ld + 1};
            }
            if (ld < rd) {
                return {r, rd + 1};
            }
            return {root, ld + 1};
        };
        return dfs(root).first;
    }
};

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
        return dfs(root).first;
    }

    pair<TreeNode*, int> dfs(TreeNode* root) {
        if (!root) return {nullptr, 0};
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        int d1 = left.second, d2 = right.second;
        if (d1 > d2) return {left.first, d1 + 1};
        if (d1 < d2) return {right.first, d2 + 1};
        return {root, d1 + 1};
    }
};
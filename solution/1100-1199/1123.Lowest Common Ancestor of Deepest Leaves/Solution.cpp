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
using pti = pair<TreeNode*, int>;
class Solution {
public:
    TreeNode* lcaDeepestLeaves(TreeNode* root) {
        return dfs(root).first;
    }

    pti dfs(TreeNode* root) {
        if (!root) return {nullptr, 0};
        pti l = dfs(root->left);
        pti r = dfs(root->right);
        int d1 = l.second, d2 = r.second;
        if (d1 > d2) return {l.first, d1 + 1};
        if (d1 < d2) return {r.first, d2 + 1};
        return {root, d1 + 1};
    }
};
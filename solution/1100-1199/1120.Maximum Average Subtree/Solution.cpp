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
    double ans;

    double maximumAverageSubtree(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    pair<int, int> dfs(TreeNode* root) {
        if (!root) return {0, 0};
        auto l = dfs(root->left);
        auto r = dfs(root->right);
        int s = l.first + root->val + r.first;
        int n = l.second + 1 + r.second;
        ans = max(ans, s * 1.0 / n);
        return {s, n};
    }
};
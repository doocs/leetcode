/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    double minimalExecTime(TreeNode* root) {
        function<pair<double, double>(TreeNode*)> dfs = [&](TreeNode* root) -> pair<double, double> {
            if (!root) {
                return {0, 0};
            }
            auto [s1, t1] = dfs(root->left);
            auto [s2, t2] = dfs(root->right);
            double s = s1 + s2 + root->val;
            double t = max({t1, t2, (s1 + s2) / 2}) + root->val;
            return {s, t};
        };
        auto [_, t] = dfs(root);
        return t;
    }
};
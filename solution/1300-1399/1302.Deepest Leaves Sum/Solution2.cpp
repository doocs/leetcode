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
    int deepestLeavesSum(TreeNode* root) {
        int mx = 0, ans = 0;
        auto dfs = [&](this auto&& dfs, TreeNode* root, int i) {
            if (!root) {
                return;
            }
            if (i == mx) {
                ans += root->val;
            } else if (i > mx) {
                mx = i;
                ans = root->val;
            }
            dfs(root->left, i + 1);
            dfs(root->right, i + 1);
        };
        dfs(root, 1);
        return ans;
    }
};

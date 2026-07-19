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
    int countDominantNodes(TreeNode* root) {
        int ans = 0;
        auto dfs = [&](this auto&& dfs, TreeNode* node) -> int {
            if (!node) {
                return INT_MIN;
            }
            int l = dfs(node->left);
            int r = dfs(node->right);
            int mx = max({l, r, node->val});
            if (mx == node->val) {
                ++ans;
            }
            return mx;
        };
        dfs(root);
        return ans;
    }
};
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
    int minDiffInBST(TreeNode* root) {
        const int inf = 1 << 30;
        int ans = inf, pre = -inf;
        auto dfs = [&](auto&& dfs, TreeNode* root) -> void {
            if (!root) {
                return;
            }
            dfs(dfs, root->left);
            ans = min(ans, root->val - pre);
            pre = root->val;
            dfs(dfs, root->right);
        };
        dfs(dfs, root);
        return ans;
    }
};
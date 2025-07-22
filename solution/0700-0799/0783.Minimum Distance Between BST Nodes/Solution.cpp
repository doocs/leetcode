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
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> void {
            if (!root) {
                return;
            }
            dfs(root->left);
            ans = min(ans, root->val - pre);
            pre = root->val;
            dfs(root->right);
        };
        dfs(root);
        return ans;
    }
};

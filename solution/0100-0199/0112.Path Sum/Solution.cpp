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
    bool hasPathSum(TreeNode* root, int targetSum) {
        function<bool(TreeNode*, int)> dfs = [&](TreeNode* root, int s) -> int {
            if (!root) return false;
            s += root->val;
            if (!root->left && !root->right && s == targetSum) return true;
            return dfs(root->left, s) || dfs(root->right, s);
        };
        return dfs(root, 0);
    }
};
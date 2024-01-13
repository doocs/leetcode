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
    int pseudoPalindromicPaths(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int mask) {
            if (!root) {
                return 0;
            }
            mask ^= 1 << root->val;
            if (!root->left && !root->right) {
                return (mask & (mask - 1)) == 0 ? 1 : 0;
            }
            return dfs(root->left, mask) + dfs(root->right, mask);
        };
        return dfs(root, 0);
    }
};
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
    int sumNumbers(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int s) -> int {
            if (!root) return 0;
            s = s * 10 + root->val;
            if (!root->left && !root->right) return s;
            return dfs(root->left, s) + dfs(root->right, s);
        };
        return dfs(root, 0);
    }
};
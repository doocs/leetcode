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
    int longestConsecutive(TreeNode* root) {
        int ans = 0;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return 0;
            }
            int l = dfs(root->left) + 1;
            int r = dfs(root->right) + 1;
            if (root->left && root->left->val - root->val != 1) {
                l = 1;
            }
            if (root->right && root->right->val - root->val != 1) {
                r = 1;
            }
            int t = max(l, r);
            ans = max(ans, t);
            return t;
        };
        dfs(root);
        return ans;
    }
};
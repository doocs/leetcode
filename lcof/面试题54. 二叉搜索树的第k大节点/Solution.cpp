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
    int kthLargest(TreeNode* root, int k) {
        int ans = 0;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root || !k) {
                return;
            }
            dfs(root->right);
            if (--k == 0) {
                ans = root->val;
            }
            dfs(root->left);
        };
        dfs(root);
        return ans;
    }
};
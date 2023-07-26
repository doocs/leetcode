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
    int heightOfTree(TreeNode* root) {
        int ans = 0;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int d) {
            ans = max(ans, d++);
            if (root->left && root->left->right != root) {
                dfs(root->left, d);
            }
            if (root->right && root->right->left != root) {
                dfs(root->right, d);
            }
        };
        dfs(root, 0);
        return ans;
    }
};
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
    TreeNode* expandBinaryTree(TreeNode* root) {
        function<TreeNode*(TreeNode*)> dfs = [&](TreeNode* root) -> TreeNode* {
            if (!root) {
                return nullptr;
            }
            TreeNode* l = dfs(root->left);
            TreeNode* r = dfs(root->right);
            if (l) {
                root->left = new TreeNode(-1, l, nullptr);
            }
            if (r) {
                root->right = new TreeNode(-1, nullptr, r);
            }
            return root;
        };
        return dfs(root);
    }
};
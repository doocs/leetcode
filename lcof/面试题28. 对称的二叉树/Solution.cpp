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
    bool isSymmetric(TreeNode* root) {
        function<bool(TreeNode*, TreeNode*)> dfs = [&](TreeNode* a, TreeNode* b) -> bool {
            if (!a && !b) {
                return true;
            }
            if (!a || !b || a->val != b->val) {
                return false;
            }
            return dfs(a->left, b->right) && dfs(a->right, b->left);
        };
        return dfs(root, root);
    }
};
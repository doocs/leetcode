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
    TreeNode* getTargetCopy(TreeNode* original, TreeNode* cloned, TreeNode* target) {
        function<TreeNode*(TreeNode*, TreeNode*)> dfs = [&](TreeNode* root1, TreeNode* root2) -> TreeNode* {
            if (root1 == nullptr) {
                return nullptr;
            }
            if (root1 == target) {
                return root2;
            }
            TreeNode* left = dfs(root1->left, root2->left);
            return left == nullptr ? dfs(root1->right, root2->right) : left;
        };
        return dfs(original, cloned);
    }
};
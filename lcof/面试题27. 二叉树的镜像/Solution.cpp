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
    TreeNode* mirrorTree(TreeNode* root) {
        // 后续遍历
        if (nullptr == root) {
            return nullptr;
        }

        mirrorTree(root->left);
        mirrorTree(root->right);
        std::swap(root->left, root->right);

        return root;

    }
};

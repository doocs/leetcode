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
    bool isSymmetric(TreeNode* left, TreeNode* right) {
        // 均为空，则直接返回 true。有且仅有一个不为空，则返回 false
        if (left == nullptr && right == nullptr) {
            return true;
        }
        if (left == nullptr || right == nullptr || left->val != right->val) {
            return false;
        }
        return isSymmetric(left->left, right->right) && isSymmetric(left->right, right->left);
    }

    bool isSymmetric(TreeNode* root) {
        if (root == nullptr) {
            return true;
        }

        return isSymmetric(root->left, root->right);
    }
};

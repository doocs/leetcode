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
    bool isSymmetric(TreeNode* a, TreeNode* b) {
        // 均为空，则直接返回true。有且仅有一个不为空，则返回false
        if (a == nullptr && b == nullptr) {
            return true;
        } else if (a == nullptr && b != nullptr) {
            return false;
        } else if (a != nullptr && b == nullptr) {
            return false;
        }

        // 判定值是否相等，和下面的节点是否对称
        return (a->val == b->val) && isSymmetric(a->left, b->right) && isSymmetric(a->right, b->left);
    }

    bool isSymmetric(TreeNode* root) {
        if (root == nullptr) {
            return true;
        }

        return isSymmetric(root->left, root->right);
    }
};

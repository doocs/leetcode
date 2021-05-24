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
    bool isBalanced(TreeNode* root) {
        if (root == nullptr) {
            return true;
        }
        int leftHeight = height(root->left);
        int rightHeight = height(root->right);
        return abs(leftHeight - rightHeight) <= 1 && isBalanced(root->left) && isBalanced(root->right);
    }
private:
    int height(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }
        int l = height(root->left);
        int r = height(root->right);
        return 1 + max(l, r);
    }
};
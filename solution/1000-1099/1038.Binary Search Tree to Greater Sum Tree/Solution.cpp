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
    int add = 0;
    TreeNode* bstToGst(TreeNode* root) {
        if (root) {
            bstToGst(root->right);
            root->val += add;
            add = root->val;
            bstToGst(root->left);
        }
        return root;
    }
};
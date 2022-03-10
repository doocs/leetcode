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
    bool checkSubTree(TreeNode* t1, TreeNode* t2) {
        if (!t2) return 1;
        if (!t1) return 0;
        if (t1->val == t2->val) return checkSubTree(t1->left, t2->left) && checkSubTree(t1->right, t2->right);
        return checkSubTree(t1->left, t2) || checkSubTree(t1->right, t2);
    }
};
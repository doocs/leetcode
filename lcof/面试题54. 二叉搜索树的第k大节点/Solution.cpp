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
    int kthLargest(TreeNode* root, int k) {
        cur = k;
        inorder(root);
        return res;
    }

private:
    int cur, res;

    void inorder(TreeNode* root) {
        if (!root) {
            return;
        }
        inorder(root->right);
        --cur;
        if (cur == 0) {
            res = root->val;
            return;
        }
        inorder(root->left);
    }
};
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
    int countNodes(TreeNode* root) {
        if (!root) return 0;
        int left = depth(root->left);
        int right = depth(root->right);
        if (left == right) return (1 << left) + countNodes(root->right);
        return (1 << right) + countNodes(root->left);
    }

    int depth(TreeNode* root) {
        int res = 0;
        for (; root != nullptr; ++res, root = root->left)
            ;
        return res;
    }
};
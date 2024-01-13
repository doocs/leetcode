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
    TreeNode* insertIntoMaxTree(TreeNode* root, int val) {
        if (root->val < val) return new TreeNode(val, root, nullptr);
        TreeNode* curr = root;
        TreeNode* node = new TreeNode(val);
        while (curr->right && curr->right->val > val) curr = curr->right;
        node->left = curr->right;
        curr->right = node;
        return root;
    }
};
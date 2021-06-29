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
        if (!root) {
            return 0;
        }
        int leftDepth = depth(root->left);
        int rightDepth = depth(root->right);
        if (leftDepth > rightDepth) {
            return (1 << rightDepth) + countNodes(root->left);
        }
        return (1 << leftDepth) + countNodes(root->right);
    }

private:
    int depth(TreeNode* root) {
        int res = 0;
        while (root) {
            ++res;
            root = root->left;
        }
        return res;
    }
};
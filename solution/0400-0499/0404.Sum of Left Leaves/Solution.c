/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int dfs(struct TreeNode* root, int isLeft) {
    if (!root) {
        return 0;
    }
    if (!root->left && !root->right) {
        return isLeft ? root->val : 0;
    }
    return dfs(root->left, 1) + dfs(root->right, 0);
}

int sumOfLeftLeaves(struct TreeNode* root) {
    return dfs(root, 0);
}

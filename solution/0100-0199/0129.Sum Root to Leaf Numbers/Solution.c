/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int dfs(struct TreeNode* root, int num) {
    if (!root) {
        return 0;
    }
    num = num * 10 + root->val;
    if (!root->left && !root->right) {
        return num;
    }
    return dfs(root->left, num) + dfs(root->right, num);
}

int sumNumbers(struct TreeNode* root) {
    return dfs(root, 0);
}

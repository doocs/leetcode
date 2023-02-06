/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode* dfs(struct TreeNode* root, struct TreeNode* cur) {
    if (!root) {
        return cur;
    }
    cur = dfs(root->left, cur);
    cur->right = malloc(sizeof(struct TreeNode));
    cur->right->val = root->val;
    cur->right->left = NULL;
    cur->right->right = NULL;
    cur = cur->right;
    return dfs(root->right, cur);
}

struct TreeNode* increasingBST(struct TreeNode* root) {
    struct TreeNode* dummy = malloc(sizeof(struct TreeNode));
    dfs(root, dummy);
    return dummy->right;
}

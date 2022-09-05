/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

#define max(a, b) (((a) > (b)) ? (a) : (b))

int dfs(struct TreeNode* root, int* res) {
    if (!root) {
        return 0;
    }
    int left = dfs(root->left, res);
    int right = dfs(root->right, res);
    *res = max(*res, left + right);
    return max(left, right) + 1;
}

int diameterOfBinaryTree(struct TreeNode* root) {
    int res = 0;
    dfs(root, &res);
    return res;
}

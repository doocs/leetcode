/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

#define max(a, b) (((a) > (b)) ? (a) : (b))

int dfs(struct TreeNode* root, int target, int* res) {
    if (!root) {
        return 0;
    }
    int left = dfs(root->left, root->val, res);
    int right = dfs(root->right, root->val, res);
    *res = max(*res, left + right);
    if (root->val == target) {
        return max(left, right) + 1;
    }
    return 0;
}

int longestUnivaluePath(struct TreeNode* root) {
    if (!root) {
        return 0;
    }
    int res = 0;
    dfs(root, root->val, &res);
    return res;
}

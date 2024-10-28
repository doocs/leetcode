/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
#define max(a, b) (((a) > (b)) ? (a) : (b))

int dfs(struct TreeNode* root, int* ans) {
    if (!root) {
        return 0;
    }
    int l = dfs(root->left, ans);
    int r = dfs(root->right, ans);
    l = root->left && root->left->val == root->val ? l + 1 : 0;
    r = root->right && root->right->val == root->val ? r + 1 : 0;
    *ans = max(*ans, l + r);
    return max(l, r);
}

int longestUnivaluePath(struct TreeNode* root) {
    int ans = 0;
    dfs(root, &ans);
    return ans;
}

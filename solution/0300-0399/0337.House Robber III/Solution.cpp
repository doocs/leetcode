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
    unordered_map<TreeNode*, int> memo;

    int rob(TreeNode* root) {
        return dfs(root);
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        if (memo.count(root)) return memo[root];
        int a = dfs(root->left) + dfs(root->right);
        int b = root->val;
        if (root->left) b += dfs(root->left->left) + dfs(root->left->right);
        if (root->right) b += dfs(root->right->left) + dfs(root->right->right);
        int res = max(a, b);
        memo[root] = res;
        return res;
    }
};
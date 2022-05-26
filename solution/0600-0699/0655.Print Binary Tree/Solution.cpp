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
    vector<vector<string>> printTree(TreeNode* root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        vector<vector<string>> ans(m, vector<string>(n, ""));
        dfs(root, ans, h, 0, (n - 1) / 2);
        return ans;
    }

    void dfs(TreeNode* root, vector<vector<string>>& ans, int h, int r, int c) {
        if (!root) return;
        ans[r][c] = to_string(root->val);
        dfs(root->left, ans, h, r + 1, c - pow(2, h - r - 1));
        dfs(root->right, ans, h, r + 1, c + pow(2, h - r - 1));
    }

    int height(TreeNode* root) {
        if (!root) return -1;
        return 1 + max(height(root->left), height(root->right));
    }
};
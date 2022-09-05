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
    int ans = 0;

    int longestZigZag(TreeNode* root) {
        dfs(root, 0, 0);
        return ans;
    }

    void dfs(TreeNode* root, int l, int r) {
        if (!root) return;
        ans = max(ans, max(l, r));
        dfs(root->left, r + 1, 0);
        dfs(root->right, 0, l + 1);
    }
};
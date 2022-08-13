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
    int ans;

    int goodNodes(TreeNode* root) {
        ans = 0;
        dfs(root, -10000);
        return ans;
    }

    void dfs(TreeNode* root, int mx) {
        if (!root) return;
        if (mx <= root->val) {
            ++ans;
            mx = root->val;
        }
        dfs(root->left, mx);
        dfs(root->right, mx);
    }
};
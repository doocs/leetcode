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

    int countUnivalSubtrees(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    bool dfs(TreeNode* root) {
        if (!root) return 1;
        bool left = dfs(root->left);
        bool right = dfs(root->right);
        bool t = 1;
        if (root->left && root->left->val != root->val) t = 0;
        if (root->right && root->right->val != root->val) t = 0;
        if (left && t && right) ++ans;
        return left && t && right;
    }
};
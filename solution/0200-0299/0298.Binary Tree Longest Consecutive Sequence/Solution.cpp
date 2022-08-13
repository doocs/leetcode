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

    int longestConsecutive(TreeNode* root) {
        ans = 1;
        dfs(root, nullptr, 1);
        return ans;
    }

    void dfs(TreeNode* root, TreeNode* p, int t) {
        if (!root) return;
        t = p != nullptr && p->val + 1 == root->val ? t + 1 : 1;
        ans = max(ans, t);
        dfs(root->left, root, t);
        dfs(root->right, root, t);
    }
};
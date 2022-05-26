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

    int maxAncestorDiff(TreeNode* root) {
        ans = 0;
        dfs(root, root->val, root->val);
        return ans;
    }

    void dfs(TreeNode* root, int mx, int mi) {
        if (!root) return;
        int t = max(abs(root->val - mx), abs(root->val - mi));
        ans = max(ans, t);
        mx = max(mx, root->val);
        mi = min(mi, root->val);
        dfs(root->left, mx, mi);
        dfs(root->right, mx, mi);
    }
};
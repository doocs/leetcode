/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    unordered_set<int> s;

    int numColor(TreeNode* root) {
        dfs(root);
        return s.size();
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        s.insert(root->val);
        dfs(root->left);
        dfs(root->right);
    }
};
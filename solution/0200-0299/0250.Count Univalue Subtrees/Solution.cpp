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
    int cnt;

    int countUnivalSubtrees(TreeNode* root) {
        if (!root) return 0;
        cnt = 0;
        dfs(root);
        return cnt;
    }

    bool dfs(TreeNode* root) {
        if (!root->left && !root->right)
        {
            ++cnt;
            return true;
        }
        bool res = true;
        if (root->left) res = dfs(root->left) && res && root->val == root->left->val;
        if (root->right) res = dfs(root->right) && res && root->val == root->right->val;
        cnt += res;
        return res;
        
    }
};
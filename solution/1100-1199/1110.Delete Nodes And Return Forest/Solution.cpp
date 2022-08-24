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
    vector<TreeNode*> delNodes(TreeNode* root, vector<int>& to_delete) {
        vector<TreeNode*> ans;
        unordered_set<int> s(to_delete.begin(), to_delete.end());
        if (!s.count(root->val)) ans.push_back(root);
        dfs(nullptr, root, s, ans);
        return ans; 
    }

    void dfs(TreeNode* fa, TreeNode* root, unordered_set<int>& s, vector<TreeNode*>& ans) {
        if (!root) return;
        dfs(root, root->left, s, ans);
        dfs(root, root->right, s, ans);
        if (s.count(root->val)) {
            if (fa && fa->left == root) fa->left = nullptr;
            if (fa && fa->right == root) fa->right = nullptr;
            if (root->left) ans.push_back(root->left);
            if (root->right) ans.push_back(root->right);
        }
    }
};
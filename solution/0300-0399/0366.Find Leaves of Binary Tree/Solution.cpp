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
    vector<vector<int>> findLeaves(TreeNode* root) {
        vector<vector<int>> res;
        TreeNode* prev = new TreeNode(0, root, nullptr);
        while (prev->left) {
            vector<int> t;
            dfs(prev->left, prev, t);
            res.push_back(t);
        }
        return res;
    }

    void dfs(TreeNode* root, TreeNode* prev, vector<int>& t) {
        if (!root) return;
        if (!root->left && !root->right) {
            t.push_back(root->val);
            if (prev->left == root)
                prev->left = nullptr;
            else
                prev->right = nullptr;
        }
        dfs(root->left, root, t);
        dfs(root->right, root, t);
    }
};
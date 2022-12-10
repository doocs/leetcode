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
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int s) {
            if (!root) return;
            s -= root->val;
            t.emplace_back(root->val);
            if (!root->left && !root->right && s == 0) ans.emplace_back(t);
            dfs(root->left, s);
            dfs(root->right, s);
            t.pop_back();
        };
        dfs(root, targetSum);
        return ans;
    }
};
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
    vector<vector<int>> ans;
    vector<int> t;
    int target;

    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        target = targetSum;
        dfs(root, 0);
        return ans;
    }

    void dfs(TreeNode* root, int s) {
        if (!root) return;
        t.push_back(root->val);
        s += root->val;
        if (!root->left && !root->right && s == target) ans.push_back(t);
        dfs(root->left, s);
        dfs(root->right, s);
        t.pop_back();
    }
};
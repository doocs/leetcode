class Solution {
public:
    vector<vector<int>> pathSum(TreeNode* root, int target) {
        vector<vector<int>> ans;
        vector<int> path;
        dfs(root, ans, path, target);
        return ans;
    }

    void dfs(TreeNode* root, vector<vector<int>>& ans, vector<int>& path, int target) {
        if (root == NULL) {
            return;
        }
        target -= root->val;
        path.push_back(root->val);
        if (root->left == NULL && root->right == NULL) {
            if (target == 0) {
                ans.push_back(vector<int>(path));
            }
        }
        dfs(root->left, ans, path, target);
        dfs(root->right, ans, path, target);
        path.pop_back();
    }
};

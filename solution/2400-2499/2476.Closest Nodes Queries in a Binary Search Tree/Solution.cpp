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
    vector<vector<int>> closestNodes(TreeNode* root, vector<int>& queries) {
        vector<int> nums;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            dfs(root->left);
            nums.push_back(root->val);
            dfs(root->right);
        };
        dfs(root);
        vector<vector<int>> ans;
        int n = nums.size();
        for (int& x : queries) {
            int i = lower_bound(nums.begin(), nums.end(), x + 1) - nums.begin() - 1;
            int j = lower_bound(nums.begin(), nums.end(), x) - nums.begin();
            int mi = i >= 0 && i < n ? nums[i] : -1;
            int mx = j >= 0 && j < n ? nums[j] : -1;
            ans.push_back({mi, mx});
        }
        return ans;
    }
};
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
    unordered_map<int, int> counter;
    int mx = 0;

    vector<int> findFrequentTreeSum(TreeNode* root) {
        mx = INT_MIN;
        dfs(root);
        vector<int> ans;
        for (auto& entry : counter)
            if (entry.second == mx)
                ans.push_back(entry.first);
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        int s = root->val + dfs(root->left) + dfs(root->right);
        ++counter[s];
        mx = max(mx, counter[s]);
        return s;
    }
};
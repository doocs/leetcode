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
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        return dfs(root1) == dfs(root2);
    }

    vector<int> dfs(TreeNode* root) {
        if (!root) return {};
        auto ans = dfs(root->left);
        auto right = dfs(root->right);
        ans.insert(ans.end(), right.begin(), right.end());
        if (ans.empty()) ans.push_back(root->val);
        return ans;
    }
};
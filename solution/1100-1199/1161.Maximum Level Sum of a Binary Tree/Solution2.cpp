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
    int maxLevelSum(TreeNode* root) {
        vector<int> s;
        dfs(root, 0, s);
        int mx = INT_MIN;
        int ans = 0;
        for (int i = 0; i < s.size(); ++i)
            if (mx < s[i]) mx = s[i], ans = i + 1;
        return ans;
    }

    void dfs(TreeNode* root, int i, vector<int>& s) {
        if (!root) return;
        if (s.size() == i)
            s.push_back(root->val);
        else
            s[i] += root->val;
        dfs(root->left, i + 1, s);
        dfs(root->right, i + 1, s);
    }
};
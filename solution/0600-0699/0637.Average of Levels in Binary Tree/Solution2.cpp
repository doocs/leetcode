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

using ll = long long;

class Solution {
public:
    vector<ll> s;
    vector<int> cnt;

    vector<double> averageOfLevels(TreeNode* root) {
        dfs(root, 0);
        vector<double> ans(s.size());
        for (int i = 0; i < s.size(); ++i) {
            ans[i] = (s[i] * 1.0 / cnt[i]);
        }
        return ans;
    }

    void dfs(TreeNode* root, int i) {
        if (!root) return;
        if (s.size() == i) {
            s.push_back(root->val);
            cnt.push_back(1);
        } else {
            s[i] += root->val;
            cnt[i]++;
        }
        dfs(root->left, i + 1);
        dfs(root->right, i + 1);
    }
};
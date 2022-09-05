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
    vector<double> averageOfLevels(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        vector<double> ans;
        while (!q.empty()) {
            int n = q.size();
            long long s = 0;
            for (int i = 0; i < n; ++i) {
                root = q.front();
                q.pop();
                s += root->val;
                if (root->left) q.push(root->left);
                if (root->right) q.push(root->right);
            }
            ans.push_back(s * 1.0 / n);
        }
        return ans;
    }
};
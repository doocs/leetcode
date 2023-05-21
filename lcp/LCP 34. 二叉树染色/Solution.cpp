/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int maxValue(TreeNode* root, int k) {
        function<vector<int>(TreeNode*)> dfs = [&](TreeNode* root) -> vector<int> {
            vector<int> ans(k + 1);
            if (!root) {
                return ans;
            }
            vector<int> l = dfs(root->left);
            vector<int> r = dfs(root->right);
            ans[0] = *max_element(l.begin(), l.end()) + *max_element(r.begin(), r.end());
            for (int i = 0; i < k; ++i) {
                for (int j = 0; j < k - i; ++j) {
                    ans[i + j + 1] = max(ans[i + j + 1], l[i] + r[j] + root->val);
                }
            }
            return ans;
        };
        vector<int> ans = dfs(root);
        return *max_element(ans.begin(), ans.end());
    }
};
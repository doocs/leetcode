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
    vector<int> findFrequentTreeSum(TreeNode* root) {
        unordered_map<int, int> cnt;
        int mx = 0;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) -> int {
            if (!root) {
                return 0;
            }
            int s = root->val + dfs(root->left) + dfs(root->right);
            mx = max(mx, ++cnt[s]);
            return s;
        };
        dfs(root);
        vector<int> ans;
        for (const auto& [k, v] : cnt) {
            if (v == mx) {
                ans.push_back(k);
            }
        }
        return ans;
    }
};

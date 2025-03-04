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
    int pathSum(TreeNode* root, int sum) {
        unordered_map<long long, int> cnt{{0, 1}};
        auto dfs = [&](this auto&& dfs, TreeNode* root, long long s) -> int {
            if (!root) {
                return 0;
            }
            s += root->val;
            int ans = cnt[s - sum];
            ++cnt[s];
            ans += dfs(root->left, s);
            ans += dfs(root->right, s);
            --cnt[s];
            return ans;
        };
        return dfs(root, 0);
    }
};

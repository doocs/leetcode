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
    int maxProduct(TreeNode* root) {
        using ll = long long;
        ll ans = 0;
        const int mod = 1e9 + 7;

        auto sum = [&](this auto&& sum, TreeNode* root) -> ll {
            if (!root) {
                return 0;
            }
            return root->val + sum(root->left) + sum(root->right);
        };

        ll s = sum(root);

        auto dfs = [&](this auto&& dfs, TreeNode* root) -> ll {
            if (!root) {
                return 0;
            }
            ll t = root->val + dfs(root->left) + dfs(root->right);
            if (t < s) {
                ans = max(ans, t * (s - t));
            }
            return t;
        };

        dfs(root);
        return ans % mod;
    }
};

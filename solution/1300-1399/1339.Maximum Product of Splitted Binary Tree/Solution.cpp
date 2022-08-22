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
const int MOD = 1e9 + 7;

class Solution {
public:
    ll ans;
    ll s;

    int maxProduct(TreeNode* root) {
        s = sum(root);
        dfs(root);
        ans %= MOD;
        return (int) ans;
    }

    ll sum(TreeNode* root) {
        if (!root) return 0;
        return root->val + sum(root->left) + sum(root->right);
    }

    ll dfs(TreeNode* root) {
        if (!root) return 0;
        ll t = root->val + dfs(root->left) + dfs(root->right);
        if (t < s) {
            ans = max(ans, t * (s - t));
        }
        return t;
    }
};
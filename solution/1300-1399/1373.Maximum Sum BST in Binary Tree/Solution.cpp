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
    int maxSumBST(TreeNode* root) {
        int ans = 0;
        const int inf = 1 << 30;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> array<int, 4> {
            if (!root) {
                return {1, inf, -inf, 0};
            }
            auto l = dfs(root->left);
            auto r = dfs(root->right);
            int v = root->val;
            if (l[0] && r[0] && l[2] < v && v < r[1]) {
                int s = l[3] + r[3] + v;
                ans = max(ans, s);
                return {1, min(l[1], v), max(r[2], v), s};
            }
            return {0};
        };
        dfs(root);
        return ans;
    }
};

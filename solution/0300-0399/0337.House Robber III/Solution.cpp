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
    int rob(TreeNode* root) {
        function<pair<int, int>(TreeNode*)> dfs = [&](TreeNode* root) -> pair<int, int> {
            if (!root) {
                return make_pair(0, 0);
            }
            auto [la, lb] = dfs(root->left);
            auto [ra, rb] = dfs(root->right);
            return make_pair(root->val + lb + rb, max(la, lb) + max(ra, rb));
        };
        auto [a, b] = dfs(root);
        return max(a, b);
    }
};
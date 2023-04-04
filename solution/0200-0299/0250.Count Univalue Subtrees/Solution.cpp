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
    int countUnivalSubtrees(TreeNode* root) {
        int ans = 0;
        function<bool(TreeNode*)> dfs = [&](TreeNode* root) -> bool {
            if (!root) {
                return true;
            }
            bool l = dfs(root->left);
            bool r = dfs(root->right);
            if (!l || !r) {
                return false;
            }
            int a = root->left ? root->left->val : root->val;
            int b = root->right ? root->right->val : root->val;
            if (a == b && b == root->val) {
                ++ans;
                return true;
            }
            return false;
        };
        dfs(root);
        return ans;
    }
};
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
    bool isCousins(TreeNode* root, int x, int y) {
        TreeNode *p1, *p2;
        int d1, d2;
        function<void(TreeNode*, TreeNode*, int)> dfs = [&](TreeNode* root, TreeNode* fa, int d) {
            if (!root) {
                return;
            }
            if (root->val == x) {
                p1 = fa;
                d1 = d;
            }
            if (root->val == y) {
                p2 = fa;
                d2 = d;
            }
            dfs(root->left, root, d + 1);
            dfs(root->right, root, d + 1);
        };
        dfs(root, nullptr, 0);
        return p1 != p2 && d1 == d2;
    }
};
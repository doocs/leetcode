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
        int d1, d2;
        TreeNode* p1;
        TreeNode* p2;
        function<void(TreeNode*, TreeNode*, int)> dfs = [&](TreeNode* root, TreeNode* parent, int depth) {
            if (!root) {
                return;
            }
            if (root->val == x) {
                d1 = depth;
                p1 = parent;
            } else if (root->val == y) {
                d2 = depth;
                p2 = parent;
            }
            dfs(root->left, root, depth + 1);
            dfs(root->right, root, depth + 1);
        };
        dfs(root, nullptr, 0);
        return p1 != p2 && d1 == d2;
    }
};
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
    int val;
    int depth;

    TreeNode* addOneRow(TreeNode* root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, nullptr);
        this->val = val;
        this->depth = depth;
        dfs(root, 1);
        return root;
    }

    void dfs(TreeNode* root, int d) {
        if (!root) return;
        if (d == depth - 1) {
            auto l = new TreeNode(val, root->left, nullptr);
            auto r = new TreeNode(val, nullptr, root->right);
            root->left = l;
            root->right = r;
            return;
        }
        dfs(root->left, d + 1);
        dfs(root->right, d + 1);
    }
};
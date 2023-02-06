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
    void recoverTree(TreeNode* root) {
        TreeNode* prev = nullptr;
        TreeNode* first = nullptr;
        TreeNode* second = nullptr;
        function<void(TreeNode * root)> dfs = [&](TreeNode* root) {
            if (!root) return;
            dfs(root->left);
            if (prev && prev->val > root->val) {
                if (!first) first = prev;
                second = root;
            }
            prev = root;
            dfs(root->right);
        };
        dfs(root);
        swap(first->val, second->val);
    }
};
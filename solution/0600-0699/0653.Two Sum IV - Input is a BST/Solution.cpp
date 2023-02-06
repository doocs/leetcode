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
    bool findTarget(TreeNode* root, int k) {
        unordered_set<int> vis;

        function<bool(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return false;
            }
            if (vis.count(k - root->val)) {
                return true;
            }
            vis.insert(root->val);
            return dfs(root->left) || dfs(root->right);
        };
        return dfs(root);
    }
};
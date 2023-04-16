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
    TreeNode* replaceValueInTree(TreeNode* root) {
        vector<int> s;
        function<void(TreeNode*, int)> dfs1 = [&](TreeNode* root, int d) {
            if (!root) {
                return;
            }
            if (s.size() <= d) {
                s.push_back(0);
            }
            s[d] += root->val;
            dfs1(root->left, d + 1);
            dfs1(root->right, d + 1);
        };
        function<void(TreeNode*, int)> dfs2 = [&](TreeNode* root, int d) {
            if (!root) {
                return;
            }
            int l = root->left ? root->left->val : 0;
            int r = root->right ? root->right->val : 0;
            if (root->left) {
                root->left->val = s[d] - l - r;
            }
            if (root->right) {
                root->right->val = s[d] - l - r;
            }
            dfs2(root->left, d + 1);
            dfs2(root->right, d + 1);
        };
        dfs1(root, 0);
        root->val = 0;
        dfs2(root, 1);
        return root;
    }
};
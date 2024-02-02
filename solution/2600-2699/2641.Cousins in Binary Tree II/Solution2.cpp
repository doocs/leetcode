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
        root->val = 0;
        vector<TreeNode*> q = {root};
        while (!q.empty()) {
            vector<TreeNode*> t;
            int s = 0;
            for (TreeNode* node : q) {
                if (node->left) {
                    t.emplace_back(node->left);
                    s += node->left->val;
                }
                if (node->right) {
                    t.emplace_back(node->right);
                    s += node->right->val;
                }
            }
            for (TreeNode* node : q) {
                int sub = (node->left ? node->left->val : 0) + (node->right ? node->right->val : 0);
                if (node->left) {
                    node->left->val = s - sub;
                }
                if (node->right) {
                    node->right->val = s - sub;
                }
            }
            q = move(t);
        }
        return root;
    }
};
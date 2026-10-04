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
        vector<TreeNode*> stk;
        if (root) {
            stk.push_back(root);
        }
        while (!stk.empty()) {
            TreeNode* node = stk.back();
            stk.pop_back();
            if (vis.count(k - node->val)) {
                return true;
            }
            vis.insert(node->val);
            if (node->right) {
                stk.push_back(node->right);
            }
            if (node->left) {
                stk.push_back(node->left);
            }
        }
        return false;
    }
};

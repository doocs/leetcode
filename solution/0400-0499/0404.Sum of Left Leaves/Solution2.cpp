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
    int sumOfLeftLeaves(TreeNode* root) {
        if (!root) {
            return 0;
        }
        int ans = 0;
        stack<TreeNode*> stk{{root}};
        while (!stk.empty()) {
            root = stk.top(), stk.pop();
            if (root->left) {
                if (!root->left->left && !root->left->right) {
                    ans += root->left->val;
                } else {
                    stk.push(root->left);
                }
            }
            if (root->right) {
                stk.push(root->right);
            }
        }
        return ans;
    }
};

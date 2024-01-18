/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        stack<TreeNode*> stk;
        TreeNode* cur = root;
        while (cur != nullptr || !stk.empty()) {
            if (cur == nullptr) {
                cur = stk.top();
                stk.pop();
                if (cur->val > p->val) {
                    return cur;
                }
                cur = cur->right;
            } else {
                stk.push(cur);
                cur = cur->left;
            }
        }
        return cur;
    }
};
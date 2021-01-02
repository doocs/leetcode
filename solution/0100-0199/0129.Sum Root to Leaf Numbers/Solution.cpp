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
    int sumNumbers(TreeNode* root) {
        if (!root) return 0;
        int res = 0;
        stack<TreeNode*> st{{root}};
        while (!st.empty()) {
            TreeNode* t = st.top();
            st.pop();
            if (!t->left && !t->right) {
                res += t->val;
            }
            if (t->right) {
                t->right->val += t->val * 10;
                st.push(t->right);
            }
            if (t->left) {
                t->left->val += t->val * 10;
                st.push(t->left);
            }
        }
        return res;
    }

};
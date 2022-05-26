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
    TreeNode* increasingBST(TreeNode* root) {
        TreeNode *head = nullptr, *tail = nullptr;
        stack<TreeNode*> stk;
        TreeNode* cur = root;
        while (!stk.empty() || cur != nullptr) {
            while (cur != nullptr) {
                stk.push(cur);
                cur = cur->left;
            }
            cur = stk.top();
            stk.pop();
            if (head == nullptr) {
                head = cur;
            } else {
                tail->right = cur;
            }
            tail = cur;
            cur->left = nullptr;
            cur = cur->right;
        }
        return head;
    }
};

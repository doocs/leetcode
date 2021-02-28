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
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        // 如果找到val，层层向上传递该root
        if (nullptr == root || p->val == root->val || q->val == root->val) {
            return root;
        }

        TreeNode* left = lowestCommonAncestor(root->left, p, q);
        TreeNode* right = lowestCommonAncestor(root->right, p, q);

        if (left != nullptr && right != nullptr) {
            // 如果两边都可以找到
            return root;
        } else if (left == nullptr) {
            // 如果左边没有找到，则直接返回右边内容
            return right;
        } else {
            return left;
        }
    }
};

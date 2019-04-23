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
    int helper(TreeNode* node, int& left, int & right) {
        left = INT_MAX;
        right = INT_MIN;
        if (node == nullptr) {
            return 0;
        }
        left = node->val;
        right = node->val;
        
        int tmpLeft, tmpRight;
        int res = 0;
        
        res = max(res, helper(node->left, tmpLeft, tmpRight));
        left = min(left, tmpLeft);
        right = max(right, tmpRight);
        
        res = max(res, helper(node->right, tmpLeft, tmpRight));
        left = min(left, tmpLeft);
        right = max(right, tmpRight);
        
        res = max(res, abs(node->val - left));
        res = max(res, abs(node->val - right));
        
        return res;
    }
public:
    int maxAncestorDiff(TreeNode* root) {
        int left = 0, right = 0;
        return helper(root, left, right);
    }
};
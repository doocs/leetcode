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
    int ans;

    int findTilt(TreeNode* root) {
        ans = 0;
        sum(root);
        return ans;
    }

    int sum(TreeNode* root) {
        if (!root) return 0;
        int left = sum(root->left), right = sum(root->right);
        ans += abs(left - right);
        return root->val + left + right;
    }
};
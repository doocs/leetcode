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
    int closestValue(TreeNode* root, double target) {
        int ans = root->val;
        double diff = INT_MAX;
        while (root) {
            double nxt = abs(root->val - target);
            if (nxt < diff || (nxt == diff && root->val < ans)) {
                diff = nxt;
                ans = root->val;
            }
            root = target < root->val ? root->left : root->right;
        }
        return ans;
    }
};
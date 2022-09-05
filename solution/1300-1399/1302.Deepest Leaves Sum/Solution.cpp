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
    int deepestLeavesSum(TreeNode* root) {
        int ans = 0;
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            ans = 0;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                ans += root->val;
                if (root->left) q.push(root->left);
                if (root->right) q.push(root->right);
            }
        }
        return ans;
    }
};
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
    bool isEvenOddTree(TreeNode* root) {
        bool even = true;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            int prev = even ? 0 : 1000000;
            for (int i = 0, n = q.size(); i < n; ++i) {
                auto node = q.front();
                q.pop();
                if (even && (prev >= node->val || node->val % 2 == 0)) return false;
                if (!even && (prev <= node->val || node->val % 2 == 1)) return false;
                prev = node->val;
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            even = !even;
        }
        return true;
    }
};
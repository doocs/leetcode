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
        int even = 1;
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            int prev = even ? 0 : 1e6;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                if (even && (root->val % 2 == 0 || prev >= root->val)) return false;
                if (!even && (root->val % 2 == 1 || prev <= root->val)) return false;
                prev = root->val;
                if (root->left) q.push(root->left);
                if (root->right) q.push(root->right);
            }
            even ^= 1;
        }
        return true;
    }
};
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
    bool isCousins(TreeNode* root, int x, int y) {
        queue<pair<TreeNode*, TreeNode*>> q;
        q.push({root, nullptr});
        int d1 = 0, d2 = 0;
        TreeNode *p1 = nullptr, *p2 = nullptr;
        for (int depth = 0; q.size(); ++depth) {
            for (int n = q.size(); n; --n) {
                auto [node, parent] = q.front();
                q.pop();
                if (node->val == x) {
                    d1 = depth;
                    p1 = parent;
                } else if (node->val == y) {
                    d2 = depth;
                    p2 = parent;
                }
                if (node->left) {
                    q.push({node->left, node});
                }
                if (node->right) {
                    q.push({node->right, node});
                }
            }
        }
        return d1 == d2 && p1 != p2;
    }
};
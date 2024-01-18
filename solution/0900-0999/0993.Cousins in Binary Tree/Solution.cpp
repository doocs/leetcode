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
        TreeNode* p1 = nullptr;
        TreeNode* p2 = nullptr;
        int d1 = 0, d2 = 0;
        queue<pair<TreeNode*, TreeNode*>> q;
        q.emplace(root, nullptr);
        int d = 0;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                auto [node, fa] = q.front();
                q.pop();
                if (node->val == x) {
                    p1 = fa;
                    d1 = d;
                }
                if (node->val == y) {
                    p2 = fa;
                    d2 = d;
                }
                if (node->left) {
                    q.emplace(node->left, node);
                }
                if (node->right) {
                    q.emplace(node->right, node);
                }
            }
            ++d;
        }
        return p1 != p2 && d1 == d2;
    }
};
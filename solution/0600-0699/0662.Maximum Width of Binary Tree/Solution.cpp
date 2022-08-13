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
    int widthOfBinaryTree(TreeNode* root) {
        queue<pair<TreeNode*, int>> q;
        q.emplace(root, 1);
        int ans = 0;
        while (!q.empty()) {
            ans = max(ans, q.back().second - q.front().second + 1);
            int start = q.front().second;
            for (int i = 0, n = q.size(); i < n; ++i) {
                auto node = q.front();
                q.pop();
                if (node.first->left != nullptr) q.emplace(node.first->left, node.second * 2 - start * 2);
                if (node.first->right != nullptr) q.emplace(node.first->right, node.second * 2 + 1 - start * 2);
            }
        }
        return ans;
    }
};
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
        q.push({root, 1});
        int ans = 0;
        while (!q.empty()) {
            ans = max(ans, q.back().second - q.front().second + 1);
            int i = q.front().second;
            for (int n = q.size(); n; --n) {
                auto p = q.front();
                q.pop();
                root = p.first;
                int j = p.second;
                if (root->left) q.push({root->left, (j << 1) - (i << 1)});
                if (root->right) q.push({root->right, (j << 1 | 1) - (i << 1)});
            }
        }
        return ans;
    }
};
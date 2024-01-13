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
    TreeNode* addOneRow(TreeNode* root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, nullptr);
        queue<TreeNode*> q{{root}};
        int i = 0;
        while (!q.empty()) {
            ++i;
            for (int k = q.size(); k; --k) {
                TreeNode* node = q.front();
                q.pop();
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
                if (i == depth - 1) {
                    node->left = new TreeNode(val, node->left, nullptr);
                    node->right = new TreeNode(val, nullptr, node->right);
                }
            }
        }
        return root;
    }
};
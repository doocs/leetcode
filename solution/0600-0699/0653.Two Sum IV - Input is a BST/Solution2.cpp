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
    bool findTarget(TreeNode* root, int k) {
        queue<TreeNode*> q{{root}};
        unordered_set<int> vis;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                TreeNode* node = q.front();
                q.pop();
                if (vis.count(k - node->val)) {
                    return true;
                }
                vis.insert(node->val);
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
        }
        return false;
    }
};
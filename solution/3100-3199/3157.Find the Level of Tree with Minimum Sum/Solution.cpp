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
    int minimumLevel(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        int ans = 0;
        long long s = 1LL << 60;
        for (int level = 1; q.size(); ++level) {
            long long t = 0;
            for (int m = q.size(); m; --m) {
                TreeNode* node = q.front();
                q.pop();
                t += node->val;
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            if (s > t) {
                s = t;
                ans = level;
            }
        }
        return ans;
    }
};
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
    TreeNode* reverseOddLevels(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        for (int i = 0; q.size(); ++i) {
            vector<TreeNode*> t;
            for (int k = q.size(); k; --k) {
                TreeNode* node = q.front();
                q.pop();
                if (i & 1) {
                    t.push_back(node);
                }
                if (node->left) {
                    q.push(node->left);
                    q.push(node->right);
                }
            }
            for (int l = 0, r = t.size() - 1; l < r; ++l, --r) {
                swap(t[l]->val, t[r]->val);
            }
        }
        return root;
    }
};
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
        int i = 0;
        vector<TreeNode*> t;
        while (!q.empty()) {
            t.clear();
            for (int n = q.size(); n; --n) {
                TreeNode* node = q.front();
                q.pop();
                if (i & 1) {
                    t.push_back(node);
                }
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            if (t.size()) {
                int j = 0, k = t.size() - 1;
                for (; j < k; ++j, --k) {
                    int v = t[j]->val;
                    t[j]->val = t[k]->val;
                    t[k]->val = v;
                }
            }
            ++i;
        }
        return root;
    }
};
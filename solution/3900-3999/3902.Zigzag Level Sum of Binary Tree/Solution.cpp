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
    vector<long long> zigzagLevelSum(TreeNode* root) {
        vector<long long> ans;
        vector<TreeNode*> q = {root};
        bool left = true;
        while (!q.empty()) {
            vector<TreeNode*> nq;
            for (TreeNode* node : q) {
                if (node->left != nullptr) {
                    nq.push_back(node->left);
                }
                if (node->right != nullptr) {
                    nq.push_back(node->right);
                }
            }
            int m = q.size();
            long long s = 0;
            for (int i = 0; i < m; i++) {
                TreeNode* node = left ? q[i] : q[m - i - 1];
                TreeNode* child = left ? node->left : node->right;
                if (child == nullptr) {
                    break;
                }
                s += node->val;
            }
            ans.push_back(s);
            left = !left;
            q = nq;
        }
        return ans;
    }
};

/**
 * Definition for a binary tree node->
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
        vector<int> p(110);
        vector<int> d(110);
        queue<TreeNode*> q;
        q.push(root);
        int i = 0;
        while (!q.empty()) {
            int n = q.size();
            while (n--) {
                auto node = q.front();
                d[node->val] = i;
                q.pop();
                if (node->left) {
                    q.push(node->left);
                    p[node->left->val] = node->val;
                }
                if (node->right) {
                    q.push(node->right);
                    p[node->right->val] = node->val;
                }
            }
            ++i;
        }
        return p[x] != p[y] && d[x] == d[y];
    }
};
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Solution {
public:
    vector<int> levelOrder(TreeNode* root) {
        vector<int> ret;
        if (!root) {
            return ret;
        }

        queue<TreeNode *> q;
        q.push(root);

        while (!q.empty()) {
            auto head = q.front();
            q.pop();
            ret.push_back(head->val);
            if (head->left) {
                q.push(head->left);
            }

            if (head->right) {
                q.push(head->right);
            }
        }

        return ret;
    }
};
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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        if (!root) return {};
        queue<TreeNode*> q {{root}};
        vector<vector<int>> ans;
        bool left = false;
        while (!q.empty()) {
            vector<int> t;
            for (int i = 0, n = q.size(); i < n; ++i) {
                auto node = q.front();
                q.pop();
                t.push_back(node->val);
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            if (left) reverse(t.begin(), t.end());
            ans.push_back(t);
            left = !left;
        }
        return ans;
    }
};
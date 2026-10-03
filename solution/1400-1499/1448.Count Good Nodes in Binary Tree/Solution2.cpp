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
    int goodNodes(TreeNode* root) {
        int ans = 0;
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(root, -1000000);
        while (!stk.empty()) {
            auto [node, mx] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (mx <= node->val) {
                ++ans;
                mx = node->val;
            }
            stk.emplace_back(node->right, mx);
            stk.emplace_back(node->left, mx);
        }
        return ans;
    }
};

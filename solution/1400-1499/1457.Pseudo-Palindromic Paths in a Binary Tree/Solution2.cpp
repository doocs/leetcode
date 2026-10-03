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
    int pseudoPalindromicPaths(TreeNode* root) {
        int ans = 0;
        vector<pair<TreeNode*, int>> stk;
        stk.emplace_back(root, 0);
        while (!stk.empty()) {
            auto [node, mask] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            mask ^= 1 << node->val;
            if (!node->left && !node->right) {
                ans += (mask & (mask - 1)) == 0;
            } else {
                stk.emplace_back(node->right, mask);
                stk.emplace_back(node->left, mask);
            }
        }
        return ans;
    }
};

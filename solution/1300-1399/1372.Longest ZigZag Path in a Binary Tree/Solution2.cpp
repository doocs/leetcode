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
    int longestZigZag(TreeNode* root) {
        int ans = 0;
        vector<tuple<TreeNode*, int, int>> stk{{root, 0, 0}};
        while (!stk.empty()) {
            auto [node, l, r] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            ans = max(ans, max(l, r));
            stk.emplace_back(node->right, 0, l + 1);
            stk.emplace_back(node->left, r + 1, 0);
        }
        return ans;
    }
};

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
    int equalToDescendants(TreeNode* root) {
        int ans = 0;
        unordered_map<TreeNode*, long long> sub;
        vector<pair<TreeNode*, int>> stk;
        if (root) {
            stk.emplace_back(root, 0);
        }
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.emplace_back(node, 1);
                if (node->right) {
                    stk.emplace_back(node->right, 0);
                }
                if (node->left) {
                    stk.emplace_back(node->left, 0);
                }
                continue;
            }
            long long l = node->left ? sub[node->left] : 0;
            long long r = node->right ? sub[node->right] : 0;
            ans += l + r == node->val;
            sub[node] = node->val + l + r;
        }
        return ans;
    }
};

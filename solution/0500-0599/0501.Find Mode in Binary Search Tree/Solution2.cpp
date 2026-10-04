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
    vector<int> findMode(TreeNode* root) {
        bool has = false;
        int prev = 0, mx = 0, cnt = 0;
        vector<int> ans;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
                continue;
            }
            cnt = has && prev == node->val ? cnt + 1 : 1;
            if (cnt > mx) {
                ans.clear();
                ans.push_back(node->val);
                mx = cnt;
            } else if (cnt == mx) {
                ans.push_back(node->val);
            }
            prev = node->val;
            has = true;
            stk.emplace_back(node->right, 0);
        }
        return ans;
    }
};

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
    string tree2str(TreeNode* root) {
        string res;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (!node) {
                    continue;
                }
                res += to_string(node->val);
                if (!node->left && !node->right) {
                    continue;
                }
                res.push_back('(');
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
                continue;
            }
            if (state == 1) {
                res.push_back(')');
                if (node->right) {
                    res.push_back('(');
                    stk.emplace_back(node, 2);
                    stk.emplace_back(node->right, 0);
                }
                continue;
            }
            res.push_back(')');
        }
        return res;
    }
};

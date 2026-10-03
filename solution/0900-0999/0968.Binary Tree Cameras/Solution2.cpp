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
    int minCameraCover(TreeNode* root) {
        if (!root) {
            return 0;
        }
        const int inf = 1 << 29;
        unordered_map<TreeNode*, array<int, 3>> sub;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
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
            array<int, 3> l = node->left ? sub[node->left] : array<int, 3>{inf, 0, 0};
            array<int, 3> r = node->right ? sub[node->right] : array<int, 3>{inf, 0, 0};
            int a = 1 + min({l[0], l[1], l[2]}) + min({r[0], r[1], r[2]});
            int b = min({l[0] + r[0], l[0] + r[1], l[1] + r[0]});
            int c = l[1] + r[1];
            sub[node] = {a, b, c};
        }
        auto ans = sub[root];
        return min(ans[0], ans[1]);
    }
};

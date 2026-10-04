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
    int amountOfTime(TreeNode* root, int start) {
        unordered_map<int, vector<int>> g;
        vector<pair<TreeNode*, TreeNode*>> stk{{root, nullptr}};
        while (!stk.empty()) {
            auto [node, fa] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (fa) {
                g[node->val].push_back(fa->val);
                g[fa->val].push_back(node->val);
            }
            stk.emplace_back(node->right, node);
            stk.emplace_back(node->left, node);
        }
        unordered_map<int, int> dist;
        vector<tuple<int, int, int>> walk{{start, -1, 0}};
        while (!walk.empty()) {
            auto [node, fa, state] = walk.back();
            walk.pop_back();
            auto& nxts = g[node];
            if (state == 0) {
                walk.emplace_back(node, fa, 1);
                for (int i = (int) nxts.size() - 1; i >= 0; --i) {
                    int nxt = nxts[i];
                    if (nxt != fa) {
                        walk.emplace_back(nxt, node, 0);
                    }
                }
            } else {
                int best = 0;
                for (int nxt : nxts) {
                    if (nxt != fa) {
                        best = max(best, 1 + dist[nxt]);
                    }
                }
                dist[node] = best;
            }
        }
        return dist[start];
    }
};

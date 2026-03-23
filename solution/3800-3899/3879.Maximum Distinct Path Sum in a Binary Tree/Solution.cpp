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
    int maxSum(TreeNode* root) {
        unordered_map<TreeNode*, vector<TreeNode*>> g;
        unordered_set<int> vis;

        auto dfs = [&](this auto&& dfs, TreeNode* node, TreeNode* p) -> void {
            if (!node) return;
            g[node].push_back(p);
            g[node].push_back(node->left);
            g[node].push_back(node->right);
            dfs(node->left, node);
            dfs(node->right, node);
        };

        auto dfs2 = [&](this auto&& dfs2, TreeNode* node) -> int {
            if (!node || vis.count(node->val)) return 0;
            vis.insert(node->val);
            int res = node->val;
            int best = 0;
            for (auto nxt : g[node]) {
                best = max(best, dfs2(nxt));
            }
            vis.erase(node->val);
            return res + best;
        };

        dfs(root, nullptr);

        int ans = INT_MIN;
        for (auto& [node, _] : g) {
            ans = max(ans, dfs2(node));
            vis.clear();
        }
        return ans;
    }
};

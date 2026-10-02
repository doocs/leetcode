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
    int rob(TreeNode* root) {
        if (!root) return 0;
        vector<TreeNode*> order;
        stack<TreeNode*> st{{root}};
        while (!st.empty()) {
            TreeNode* node = st.top();
            st.pop();
            order.push_back(node);
            if (node->left) st.push(node->left);
            if (node->right) st.push(node->right);
        }
        unordered_map<TreeNode*, pair<int, int>> dp;
        for (auto it = order.rbegin(); it != order.rend(); ++it) {
            TreeNode* node = *it;
            auto left = node->left ? dp[node->left] : pair<int, int>{0, 0};
            auto right = node->right ? dp[node->right] : pair<int, int>{0, 0};
            dp[node] = {node->val + left.second + right.second, max(left.first, left.second) + max(right.first, right.second)};
        }
        auto [a, b] = dp[root];
        return max(a, b);
    }
};

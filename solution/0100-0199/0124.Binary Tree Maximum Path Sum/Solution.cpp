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
    int maxPathSum(TreeNode* root) {
        int ans = -1001;
        vector<pair<TreeNode*, bool>> stack{{root, false}};
        unordered_map<TreeNode*, int> gains;
        while (!stack.empty()) {
            auto [node, visited] = stack.back();
            stack.pop_back();
            if (!visited) {
                stack.emplace_back(node, true);
                if (node->right) {
                    stack.emplace_back(node->right, false);
                }
                if (node->left) {
                    stack.emplace_back(node->left, false);
                }
                continue;
            }
            int left = node->left ? max(0, gains[node->left]) : 0;
            int right = node->right ? max(0, gains[node->right]) : 0;
            ans = max(ans, left + right + node->val);
            gains[node] = node->val + max(left, right);
        }
        return ans;
    }
};

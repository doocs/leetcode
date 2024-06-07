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
    int closestValue(TreeNode* root, double target) {
        int ans = root->val;
        double diff = INT_MAX;
        function<void(TreeNode*)> dfs = [&](TreeNode* node) {
            if (!node) {
                return;
            }
            double nxt = abs(node->val - target);
            if (nxt < diff || (nxt == diff && node->val < ans)) {
                diff = nxt;
                ans = node->val;
            }
            node = target < node->val ? node->left : node->right;
            dfs(node);
        };
        dfs(root);
        return ans;
    }
};
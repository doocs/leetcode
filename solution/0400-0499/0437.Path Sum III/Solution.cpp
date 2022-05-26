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
    int pathSum(TreeNode* root, int targetSum) {
        unordered_map<int, int> preSum;
        preSum[0] = 1;

        function<int(TreeNode*, int)> dfs = [&](TreeNode* node, int cur) {
            if (node == nullptr) {
                return 0;
            }

            cur += node->val;
            int ret = preSum[cur - targetSum];

            ++preSum[cur];
            ret += dfs(node->left, cur);
            ret += dfs(node->right, cur);
            --preSum[cur];

            return ret;
        };

        return dfs(root, 0);
    }
};

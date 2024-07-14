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
    int sumEvenGrandparent(TreeNode* root) {
        function<int(TreeNode*, int)> dfs = [&](TreeNode* root, int x) {
            if (!root) {
                return 0;
            }
            int ans = dfs(root->left, root->val) + dfs(root->right, root->val);
            if (x % 2 == 0) {
                if (root->left) {
                    ans += root->left->val;
                }
                if (root->right) {
                    ans += root->right->val;
                }
            }
            return ans;
        };
        return dfs(root, 1);
    }
};
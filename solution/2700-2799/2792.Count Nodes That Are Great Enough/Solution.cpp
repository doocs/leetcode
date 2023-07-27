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
    int countGreatEnoughNodes(TreeNode* root, int k) {
        int ans = 0;
        function<priority_queue<int>(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return priority_queue<int>();
            }
            auto left = dfs(root->left);
            auto right = dfs(root->right);
            while (right.size()) {
                left.push(right.top());
                right.pop();
                if (left.size() > k) {
                    left.pop();
                }
            }
            if (left.size() == k && left.top() < root->val) {
                ++ans;
            }
            left.push(root->val);
            if (left.size() > k) {
                left.pop();
            }
            return left;
        };
        dfs(root);
        return ans;
    }
};
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
    bool isValidSequence(TreeNode* root, vector<int>& arr) {
        function<bool(TreeNode*, int)> dfs = [&](TreeNode* root, int u) -> bool {
            if (!root || root->val != arr[u]) return false;
            if (u == arr.size() - 1) return !root->left && !root->right;
            return dfs(root->left, u + 1) || dfs(root->right, u + 1);
        };
        return dfs(root, 0);
    }
};
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
    vector<int> flipMatchVoyage(TreeNode* root, vector<int>& voyage) {
        bool ok = true;
        int i = 0;
        vector<int> ans;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root || !ok) {
                return;
            }
            if (root->val != voyage[i]) {
                ok = false;
                return;
            }
            ++i;
            if (!root->left || root->left->val == voyage[i]) {
                dfs(root->left);
                dfs(root->right);
            } else {
                ans.push_back(root->val);
                dfs(root->right);
                dfs(root->left);
            }
        };
        dfs(root);
        return ok ? ans : vector<int>{-1};
    }
};
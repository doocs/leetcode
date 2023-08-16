/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int getNumber(TreeNode* root, vector<vector<int>>& ops) {
        set<int> ts;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (root == nullptr) {
                return;
            }
            ts.insert(root->val);
            dfs(root->left);
            dfs(root->right);
        };
        dfs(root);
        int ans = 0;
        for (int i = ops.size() - 1; ~i; --i) {
            int t = ops[i][0];
            int x = ops[i][1], y = ops[i][2];
            auto it = ts.lower_bound(x);
            while (it != ts.end() && *it <= y) {
                ts.erase(it++);
                ans += t;
            }
        }
        return ans;
    }
};
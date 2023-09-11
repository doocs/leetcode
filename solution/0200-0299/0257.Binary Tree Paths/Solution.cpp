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
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> ans;
        vector<string> t;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            t.push_back(to_string(root->val));
            if (!root->left && !root->right) {
                ans.push_back(join(t));
            } else {
                dfs(root->left);
                dfs(root->right);
            }
            t.pop_back();
        };
        dfs(root);
        return ans;
    }

    string join(vector<string>& t, string sep = "->") {
        string ans;
        for (int i = 0; i < t.size(); ++i) {
            if (i > 0) {
                ans += sep;
            }
            ans += t[i];
        }
        return ans;
    }
};
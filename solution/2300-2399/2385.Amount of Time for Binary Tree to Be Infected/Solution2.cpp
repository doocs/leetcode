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
    unordered_map<int, vector<int>> g;

    int amountOfTime(TreeNode* root, int start) {
        dfs(root);
        return dfs(start, -1);
    }

    int dfs(int i, int fa) {
        int ans = 0;
        for (int& j : g[i]) {
            if (j != fa) {
                ans = max(ans, 1 + dfs(j, i));
            }
        }
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        if (root->left) {
            g[root->val].push_back(root->left->val);
            g[root->left->val].push_back(root->val);
        }
        if (root->right) {
            g[root->val].push_back(root->right->val);
            g[root->right->val].push_back(root->val);
        }
        dfs(root->left);
        dfs(root->right);
    }
};
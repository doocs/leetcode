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
        queue<int> q {{start}};
        unordered_set<int> vis;
        int ans = -1;
        while (q.size()) {
            ++ans;
            for (int n = q.size(); n; --n) {
                int i = q.front();
                q.pop();
                vis.insert(i);
                for (int j : g[i]) {
                    if (!vis.count(j)) {
                        q.push(j);
                    }
                }
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
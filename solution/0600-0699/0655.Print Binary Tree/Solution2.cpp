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
    vector<vector<string>> printTree(TreeNode* root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        vector<vector<string>> ans(m, vector<string>(n, ""));
        queue<tuple<TreeNode*, int, int>> q;
        q.push({root, 0, (n - 1) / 2});
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            root = get<0>(p);
            int r = get<1>(p), c = get<2>(p);
            ans[r][c] = to_string(root->val);
            if (root->left) q.push({root->left, r + 1, c - pow(2, h - r - 1)});
            if (root->right) q.push({root->right, r + 1, c + pow(2, h - r - 1)});
        }
        return ans;
    }

    int height(TreeNode* root) {
        int h = -1;
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            ++h;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                if (root->left) q.push(root->left);
                if (root->right) q.push(root->right);
            }
        }
        return h;
    }
};
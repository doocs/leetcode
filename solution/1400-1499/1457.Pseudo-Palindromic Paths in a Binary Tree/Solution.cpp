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
    int ans;
    vector<int> counter;

    int pseudoPalindromicPaths(TreeNode* root) {
        ans = 0;
        counter.resize(10);
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        ++counter[root->val];
        if (!root->left && !root->right) {
            int n = 0;
            for (int i = 1; i < 10; ++i)
                if (counter[i] % 2 == 1)
                    ++n;
            if (n < 2) ++ans;
        } else {
            dfs(root->left);
            dfs(root->right);
        }
        --counter[root->val];
    }
};
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
    int maxLevelSum(TreeNode* root) {
        vector<int> ans(2);
        ans[0] = INT_MIN;
        queue<TreeNode*> q{{root}};
        int l = 0;
        while (!q.empty())
        {
            ++l;
            int s = 0;
            for (int i = q.size(); i > 0; --i)
            {
                TreeNode* node = q.front();
                q.pop();
                s += node->val;
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            if (s > ans[0])
            {
                ans[0] = s;
                ans[1] = l;
            }
        }
        return ans[1];
    }
};
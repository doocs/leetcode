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
    vector<int> getAllElements(TreeNode* root1, TreeNode* root2) {
        vector<int> t1;
        vector<int> t2;
        dfs(root1, t1);
        dfs(root2, t2);
        return merge(t1, t2);
    }

    void dfs(TreeNode* root, vector<int>& t) {
        if (!root) return;
        dfs(root->left, t);
        t.push_back(root->val);
        dfs(root->right, t);
    }

    vector<int> merge(vector<int>& t1, vector<int>& t2) {
        vector<int> ans;
        int i = 0, j = 0;
        while (i < t1.size() && j < t2.size()) {
            if (t1[i] <= t2[j])
                ans.push_back(t1[i++]);
            else
                ans.push_back(t2[j++]);
        }
        while (i < t1.size()) ans.push_back(t1[i++]);
        while (j < t2.size()) ans.push_back(t2[j++]);
        return ans;
    }
};
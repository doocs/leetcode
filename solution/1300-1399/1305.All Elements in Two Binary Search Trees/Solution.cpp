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
        vector<int> a, b, ans;
        dfs(root1, a);
        dfs(root2, b);

        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a[i] <= b[j]) {
                ans.push_back(a[i++]);
            } else {
                ans.push_back(b[j++]);
            }
        }
        while (i < a.size()) {
            ans.push_back(a[i++]);
        }
        while (j < b.size()) {
            ans.push_back(b[j++]);
        }
        return ans;
    }

private:
    void dfs(TreeNode* root, vector<int>& nums) {
        if (root == nullptr) {
            return;
        }
        dfs(root->left, nums);
        nums.push_back(root->val);
        dfs(root->right, nums);
    }
};

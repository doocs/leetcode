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
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        return dfs(nums, 0, nums.size() - 1);
    }

    TreeNode* dfs(vector<int>& nums, int l, int r) {
        if (l > r) return nullptr;
        int i = l;
        for (int j = l; j <= r; ++j) {
            if (nums[i] < nums[j]) {
                i = j;
            }
        }
        TreeNode* root = new TreeNode(nums[i]);
        root->left = dfs(nums, l, i - 1);
        root->right = dfs(nums, i + 1, r);
        return root;
    }
};
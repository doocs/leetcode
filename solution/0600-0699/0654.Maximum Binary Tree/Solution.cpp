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
        return construct(nums, 0, nums.size() - 1);
    }

    TreeNode* construct(vector<int>& nums, int l, int r) {
        if (l > r) return nullptr;
        int mx = l;
        for (int i = l + 1; i <= r; ++i) {
            if (nums[mx] < nums[i]) mx = i;
        }
        TreeNode* root = new TreeNode(nums[mx]);
        root->left = construct(nums, l, mx - 1);
        root->right = construct(nums, mx + 1, r);
        return root;
    }
};
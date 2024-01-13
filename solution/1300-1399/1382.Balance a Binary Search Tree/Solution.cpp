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
    TreeNode* balanceBST(TreeNode* root) {
        dfs(root);
        return build(0, nums.size() - 1);
    }

private:
    vector<int> nums;

    void dfs(TreeNode* root) {
        if (!root) {
            return;
        }
        dfs(root->left);
        nums.push_back(root->val);
        dfs(root->right);
    }

    TreeNode* build(int i, int j) {
        if (i > j) {
            return nullptr;
        }
        int mid = (i + j) >> 1;
        TreeNode* left = build(i, mid - 1);
        TreeNode* right = build(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    }
};
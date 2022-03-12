class Solution {
public:
    TreeNode* sufficientSubset(TreeNode* root, int limit) {
        if (root == nullptr) return nullptr;

        limit -= root->val;
        if (root->left == nullptr && root->right == nullptr)
            return limit > 0 ? nullptr : root;

        root->left = sufficientSubset(root->left, limit);
        root->right = sufficientSubset(root->right, limit);

        if (root->left == nullptr && root->right == nullptr)
            return nullptr;
        return root;
    }
};

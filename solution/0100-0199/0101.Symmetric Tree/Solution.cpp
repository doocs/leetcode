class Solution {
public:
    bool isSymmetric(TreeNode* root) {
    	if (!root) return true;
    	return isSymmetric(root->left, root->right);
    }

private:
    bool isSymmetric(TreeNode* left, TreeNode* right) {
        if (!left && !right) return true;
        if (!left && right || left && !right || left->val != right->val) return false;
        return isSymmetric(left->left, right->right) && isSymmetric(left->right, right->left);
    }
};

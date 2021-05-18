class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        return build(preorder, inorder, 0, preorder.size() - 1, 0, inorder.size() - 1);
    }

private:
    TreeNode* build(vector<int>& preorder, vector<int>& inorder, int pre_l, int pre_r, int in_l, int in_r) {
        if (pre_l > pre_r || in_l > in_r) {
            return NULL;
        }
        int root = preorder[pre_l];
        int i = in_l;
        while (i <= in_r && inorder[i] != root) {
            ++i;
        }
        TreeNode* node = new TreeNode(root);
        node->left = build(preorder, inorder, pre_l + 1, pre_l + i - in_l, in_l, i - 1);
        node->right = build(preorder, inorder, pre_l + i - in_l + 1, pre_r, i + 1, in_r);
        return node;
    }
};

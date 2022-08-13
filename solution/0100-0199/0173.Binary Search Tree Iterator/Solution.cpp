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
class BSTIterator {
public:
    stack<TreeNode*> stack;
    BSTIterator(TreeNode* root) {
        for (; root != nullptr; root = root->left) {
            stack.push(root);
        }
    }

    int next() {
        TreeNode* cur = stack.top();
        stack.pop();
        TreeNode* node = cur->right;
        for (; node != nullptr; node = node->left) {
            stack.push(node);
        }
        return cur->val;
    }

    bool hasNext() {
        return !stack.empty();
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
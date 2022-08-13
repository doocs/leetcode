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
    TreeNode* correctBinaryTree(TreeNode* root) {
        queue<TreeNode*> q;
        q.push(root);
        unordered_set<TreeNode*> s;
        while (!q.empty()) {
            int n = q.size();
            while (n--) {
                TreeNode* node = q.front();
                q.pop();
                if (node->right) {
                    if (s.count(node->right->right)) {
                        node->right = nullptr;
                        return root;
                    }
                    q.push(node->right);
                    s.insert(node->right);
                }
                if (node->left) {
                    if (s.count(node->left->right)) {
                        node->left = nullptr;
                        return root;
                    }
                    q.push(node->left);
                    s.insert(node->left);
                }
            }
        }
        return root;
    }
};
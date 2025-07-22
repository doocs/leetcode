/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Codec {
public:
    // Encodes an n-ary tree to a binary tree.
    TreeNode* encode(Node* root) {
        if (root == nullptr) {
            return nullptr;
        }
        TreeNode* node = new TreeNode(root->val);
        if (root->children.empty()) {
            return node;
        }
        TreeNode* left = encode(root->children[0]);
        node->left = left;
        for (int i = 1; i < root->children.size(); i++) {
            left->right = encode(root->children[i]);
            left = left->right;
        }
        return node;
    }

    // Decodes your binary tree to an n-ary tree.
    Node* decode(TreeNode* data) {
        if (data == nullptr) {
            return nullptr;
        }
        Node* node = new Node(data->val, vector<Node*>());
        if (data->left == nullptr) {
            return node;
        }
        TreeNode* left = data->left;
        while (left != nullptr) {
            node->children.push_back(decode(left));
            left = left->right;
        }
        return node;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.decode(codec.encode(root));

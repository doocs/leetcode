/*
// Definition for a Node->
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* parent;
};
*/

class Solution {
public:
    Node* flipBinaryTree(Node* root, Node* leaf) {
        Node* cur = leaf;
        Node* p = cur->parent;
        while (cur != root) {
            Node* gp = p->parent;
            if (cur->left) {
                cur->right = cur->left;
            }
            cur->left = p;
            p->parent = cur;
            if (p->left == cur) {
                p->left = nullptr;
            } else if (p->right == cur) {
                p->right = nullptr;
            }
            cur = p;
            p = gp;
        }
        leaf->parent = nullptr;
        return leaf;
    }
};
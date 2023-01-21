/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    Node* connect(Node* root) {
        Node* node = root;
        Node* prev = nullptr;
        Node* next = nullptr;
        auto modify = [&](Node* curr) {
            if (!curr) {
                return;
            }
            if (!next) {
                next = curr;
            }
            if (prev) {
                prev->next = curr;
            }
            prev = curr;
        };
        while (node) {
            prev = next = nullptr;
            while (node) {
                modify(node->left);
                modify(node->right);
                node = node->next;
            }
            node = next;
        }
        return root;
    }
};
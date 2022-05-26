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
        if (!root || (!root->left && !root->right)) {
            return root;
        }
        queue<Node*> q;
        q.push(root);
        while (!q.empty()) {
            Node* cur = nullptr;
            for (int i = 0, n = q.size(); i < n; ++i) {
                Node* node = q.front();
                q.pop();
                if (node->right) {
                    q.push(node->right);
                }
                if (node->left) {
                    q.push(node->left);
                }
                node->next = cur;
                cur = node;
            }
        }
        return root;
    }
};
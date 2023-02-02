/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;

    Node() {}

    Node(int _val) {
        val = _val;
        left = NULL;
        right = NULL;
    }

    Node(int _val, Node* _left, Node* _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
public:
    Node* treeToDoublyList(Node* root) {
        if (!root) {
            return nullptr;
        }
        Node* pre = nullptr;
        Node* head = nullptr;
        function<void(Node*)> dfs = [&](Node* root) {
            if (!root) {
                return;
            }
            dfs(root->left);
            if (pre) {
                pre->right = root;
            } else {
                head = root;
            }
            root->left = pre;
            pre = root;
            dfs(root->right);
        };

        dfs(root);
        head->left = pre;
        pre->right = head;
        return head;
    }
};
class Solution {
public:
    Node* treeToDoublyList(Node* root) {
        if (root == NULL) return NULL;
        inorder(root);
        head->left = pre;
        pre->right = head;
        return head;
    }

private:
    Node *pre, *head;

    void inorder(Node* cur) {
        if (cur) {
            inorder(cur->left);
            if (pre)
                pre->right = cur;
            else
                head = cur;
            cur->left = pre;
            pre = cur;
            inorder(cur->right);
        }
    }
};

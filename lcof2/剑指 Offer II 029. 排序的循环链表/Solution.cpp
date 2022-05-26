/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;

    Node() {}

    Node(int _val) {
        val = _val;
        next = NULL;
    }

    Node(int _val, Node* _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
public:
    Node* insert(Node* head, int insertVal) {
        Node* insert = new Node(insertVal);
        if (head == nullptr) {
            head = insert;
            head->next = head;
        } else if (head->next == nullptr) {
            head->next = insert;
            insert->next = head;
        } else {
            insertCore(head, insert);
        }

        return head;
    }

    void insertCore(Node* head, Node* insert) {
        Node* cur = head;
        Node* maxNode = head;
        Node* next = head->next;

        while (!(cur->val <= insert->val && insert->val <= next->val) && next != head) {
            cur = cur->next;
            next = next->next;

            if (cur->val >= maxNode->val)
                maxNode = cur;
        }

        if (cur->val <= insert->val && insert->val <= next->val) {
            insert->next = next;
            cur->next = insert;
        } else {
            insert->next = maxNode->next;
            maxNode->next = insert;

        }

    }
};
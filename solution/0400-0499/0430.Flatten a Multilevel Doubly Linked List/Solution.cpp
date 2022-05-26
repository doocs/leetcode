/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* prev;
    Node* next;
    Node* child;
};
*/

class Solution {
public:
    Node* flatten(Node* head) {
        flattenGetTail(head);
        return head;
    }

    Node* flattenGetTail(Node* head) {
        Node* cur = head;
        Node* tail = nullptr;

        while (cur) {
            Node* next = cur->next;
            if (cur->child) {
                Node* child = cur->child;
                Node* childTail = flattenGetTail(cur->child);

                cur->child = nullptr;
                cur->next = child;
                child->prev = cur;
                childTail->next = next;

                if (next)
                    next->prev = childTail;

                tail = childTail;
            } else {
                tail = cur;
            }

            cur = next;
        }

        return tail;
    }
};
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/
class Solution {
public:
    Node* copyRandomList(Node* head) {
        if (!head) {
            return nullptr;
        }
        for (Node* cur = head; cur;) {
            Node* node = new Node(cur->val);
            node->next = cur->next;
            cur->next = node;
            cur = node->next;
        }
        for (Node* cur = head; cur; cur = cur->next->next) {
            if (cur->random) {
                cur->next->random = cur->random->next;
            }
        }
        Node* ans = head->next;
        for (Node* cur = head; cur;) {
            Node* nxt = cur->next;
            if (nxt) {
                cur->next = nxt->next;
            }
            cur = nxt;
        }
        return ans;
    }
};
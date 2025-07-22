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
        Node* cur = head;
        while (cur != nullptr) {
            Node* node = new Node(cur->val);
            node->next = cur->next;
            cur->next = node;
            cur = node->next;
        }
        cur = head;
        while (cur != nullptr) {
            cur->next->random = cur->random == nullptr ? nullptr : cur->random->next;
            cur = cur->next->next;
        }
        cur = head;
        Node* ans = head->next;
        while (cur->next != nullptr) {
            Node* node = cur->next;
            cur->next = node->next;
            cur = node;
        }
        return ans;
    }
};

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
        Node* dummy = new Node(0);
        Node* tail = dummy;
        unordered_map<Node*, Node*> d;
        for (Node* cur = head; cur; cur = cur->next) {
            Node* node = new Node(cur->val);
            tail->next = node;
            tail = node;
            d[cur] = node;
        }
        for (Node* cur = head; cur; cur = cur->next) {
            d[cur]->random = cur->random ? d[cur->random] : nullptr;
        }
        return dummy->next;
    }
};

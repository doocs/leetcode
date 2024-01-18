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
        unordered_map<Node*, Node*> d;
        Node* dummy = new Node(0);
        Node* tail = dummy;
        for (auto cur = head; cur; cur = cur->next) {
            tail->next = new Node(cur->val);
            tail = tail->next;
            d[cur] = tail;
        }
        tail = dummy->next;
        for (auto cur = head; cur; cur = cur->next) {
            tail->random = d[cur->random];
            tail = tail->next;
        }
        return dummy->next;
    }
};
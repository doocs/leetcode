/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
        ListNode* d1 = new ListNode();
        ListNode* d2 = new ListNode();
        ListNode* t1 = d1;
        ListNode* t2 = d2;
        while (head) {
            if (head->val < x) {
                t1->next = head;
                t1 = t1->next;
            } else {
                t2->next = head;
                t2 = t2->next;
            }
            head = head->next;
        }
        t1->next = d2->next;
        t2->next = nullptr;
        return d1->next;
    }
};
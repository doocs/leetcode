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
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* p = dummy;
        ListNode* q = dummy;
        while (n-- > 0) {
            p = p->next;
        }
        while (p->next != nullptr) {
            p = p->next;
            q = q->next;
        }
        q->next = q->next->next;
        return dummy->next;
    }
};
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* deleteNode(ListNode* head, int val) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* pre = dummy;
        for (; pre->next && pre->next->val != val; pre = pre->next)
            ;
        pre->next = pre->next ? pre->next->next : nullptr;
        return dummy->next;
    }
};
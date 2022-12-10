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
    ListNode* plusOne(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* target = dummy;
        while (head) {
            if (head->val != 9) target = head;
            head = head->next;
        }
        ++target->val;
        target = target->next;
        while (target) {
            target->val = 0;
            target = target->next;
        }
        return dummy->val == 1 ? dummy : dummy->next;
    }
};
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
        for (ListNode* cur = dummy; cur->next; cur = cur->next) {
            if (cur->next->val == val) {
                cur->next = cur->next->next;
                break;
            }
        }
        return dummy->next;
    }
};
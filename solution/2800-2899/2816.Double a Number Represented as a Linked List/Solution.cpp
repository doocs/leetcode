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
    ListNode* doubleIt(ListNode* head) {
        head = reverse(head);
        ListNode* dummy = new ListNode();
        ListNode* cur = dummy;
        int mul = 2, carry = 0;
        while (head) {
            int x = head->val * mul + carry;
            carry = x / 10;
            cur->next = new ListNode(x % 10);
            cur = cur->next;
            head = head->next;
        }
        if (carry) {
            cur->next = new ListNode(carry);
        }
        return reverse(dummy->next);
    }

    ListNode* reverse(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* cur = head;
        while (cur) {
            ListNode* next = cur->next;
            cur->next = dummy->next;
            dummy->next = cur;
            cur = next;
        }
        return dummy->next;
    }
};
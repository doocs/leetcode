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
    bool isPalindrome(ListNode* head) {
        if (!head) {
            return true;
        }
        ListNode* slow = head;
        ListNode* fast = head->next;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        ListNode* p = slow->next;
        slow->next = nullptr;
        ListNode* dummy = new ListNode(0);
        while (p) {
            ListNode* next = p->next;
            p->next = dummy->next;
            dummy->next = p;
            p = next;
        }
        p = dummy->next;
        while (p) {
            if (head->val != p->val) {
                return false;
            }
            head = head->next;
            p = p->next;
        }
        return true;
    }
};
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
    ListNode* insertGreatestCommonDivisors(ListNode* head) {
        ListNode* pre = head;
        for (ListNode* cur = head->next; cur; cur = cur->next) {
            int x = gcd(pre->val, cur->val);
            pre->next = new ListNode(x, cur);
            pre = cur;
        }
        return head;
    }
};
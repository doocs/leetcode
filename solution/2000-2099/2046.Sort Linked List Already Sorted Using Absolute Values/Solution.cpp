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
    ListNode* sortLinkedList(ListNode* head) {
        ListNode* prev = head;
        ListNode* curr = head->next;
        while (curr) {
            if (curr->val < 0) {
                auto t = curr->next;
                prev->next = t;
                curr->next = head;
                head = curr;
                curr = t;
            } else {
                prev = curr;
                curr = curr->next;
            }
        }
        return head;
    }
};
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
    ListNode* rotateRight(ListNode* head, int k) {
        if (k == 0 || !head || !head->next) {
            return head;
        }
        int n = 0;
        for (ListNode* cur = head; !!cur; cur = cur->next) {
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        ListNode *slow = head, *fast = head;
        while (k-- > 0) {
            fast = fast->next;
        }
        while (fast->next) {
            slow = slow->next;
            fast = fast->next;
        }

        ListNode* start = slow->next;
        slow->next = nullptr;
        fast->next = head;
        return start;
    }
};
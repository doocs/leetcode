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
    int pairSum(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head->next;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        ListNode* pa = head;
        ListNode* q = slow->next;
        slow->next = nullptr;
        ListNode* pb = reverse(q);
        int ans = 0;
        while (pa) {
            ans = max(ans, pa->val + pb->val);
            pa = pa->next;
            pb = pb->next;
        }
        return ans;
    }

    ListNode* reverse(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* curr = head;
        while (curr) {
            ListNode* next = curr->next;
            curr->next = dummy->next;
            dummy->next = curr;
            curr = next;
        }
        return dummy->next;
    }
};
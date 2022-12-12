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
    ListNode* deleteDuplicatesUnsorted(ListNode* head) {
        unordered_map<int, int> cnt;
        for (ListNode* cur = head; cur; cur = cur->next) {
            cnt[cur->val]++;
        }
        ListNode* dummy = new ListNode(0, head);
        for (ListNode *pre = dummy, *cur = head; cur; cur = cur->next) {
            if (cnt[cur->val] > 1) {
                pre->next = cur->next;
            } else {
                pre = cur;
            }
        }
        return dummy->next;
    }
};
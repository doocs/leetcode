/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

int getDecimalValue(struct ListNode* head) {
    int ans = 0;
    struct ListNode* cur = head;
    while (cur) {
        ans = (ans << 1) | cur->val;
        cur = cur->next;
    }
    return ans;
}

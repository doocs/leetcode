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
    ListNode* deleteNodes(ListNode* head, int m, int n) {
        auto pre = head;
        while (pre) {
            for (int i = 0; i < m - 1 && pre; ++i) {
                pre = pre->next;
            }
            if (!pre) {
                return head;
            }
            auto cur = pre;
            for (int i = 0; i < n && cur; ++i) {
                cur = cur->next;
            }
            pre->next = cur ? cur->next : nullptr;
            pre = pre->next;
        }
        return head;
    }
};
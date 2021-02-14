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
        ListNode* cur = head;
        if (!head) {
            return nullptr;
        }

        if (head->val == val) {
            // 第一个就匹配的情况
            return head->next;
        }
    
        while (cur && cur->next) {
            if (cur->next->val == val) {
                // 如果找到了，直接指向下一个
                cur->next = cur->next->next;
                break;
            } else {
                cur = cur->next;
            }
        }

        return head;
    }
};
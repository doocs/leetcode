class Solution {
public:
    ListNode* reverseList(ListNode* head) {
        // 通过头插实现逆序
        // ListNode *first = new ListNode(-1);
        // ListNode *p = head, *q;
        // while (p) {
        //     q = p->next;
        //     p->next = first->next;
        //     first->next = p;
        //     p = q;
        // }
        // return first->next;

        // 常规方法
        ListNode *pre = NULL, *cur = head;
        while (cur) {
            ListNode* temp = cur->next;
            cur->next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
};

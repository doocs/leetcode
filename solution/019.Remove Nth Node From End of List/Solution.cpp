class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode *p, *q ;
        q = head ;
        p = head ;
        
        for (int i = 0; i < n; ++i)
            q = q->next ;
        
        if (!q)
        {
            return head->next ;
        }
        
        while (q->next)
        {
            q = q->next ;
            p = p->next ;
        }

        p->next = p->next->next ;
        return head ;
    }
};
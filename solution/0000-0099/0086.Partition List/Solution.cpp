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
    ListNode* partition(ListNode* head, int x) {
        ListNode headSmaller(0), headBigger(0) ;
        ListNode *pSmaller = &headSmaller ;
        ListNode *pBigger = &headBigger ;
        ListNode *p = head ;
        while (nullptr != p)
        {
            if (p->val < x)
            {
                pSmaller->next = p ;
                pSmaller = p ;
            }
            else
            {
                pBigger->next = p ;
                pBigger = p ;
            }
            p = p->next ;
        }
        pBigger->next = nullptr ;
        pSmaller->next = headBigger.next ;
        return headSmaller.next ;
    }
};

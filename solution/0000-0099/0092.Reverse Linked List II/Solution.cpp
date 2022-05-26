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
    ListNode* reverseBetween(ListNode* head, int m, int n) {
        ListNode headNode(0) ;
        headNode.next = head ;
        
        n = n-m+1 ;
        
        ListNode *p = &headNode ;
        while (--m) // 移动m-1次，故--m
            p = p->next ;
        
        ListNode revNode(0) ;
        ListNode *pRev = &revNode ;
        pRev->next = nullptr ;
        ListNode *pN = p->next ;
        
        ListNode *pTmp ;
        while (n--)
        {
            pTmp = p->next ;
            p->next = p->next->next ;
            pTmp->next = pRev->next ;
            pRev->next = pTmp ;
        }
        
        pN->next = p->next ;
        p->next = revNode.next ;
        
        return headNode.next ;
    }
};

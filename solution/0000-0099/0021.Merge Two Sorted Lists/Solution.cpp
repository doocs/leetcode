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
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode *a1=new ListNode(0);
        ListNode *head=a1;
        while(l1!=NULL&&l2!=NULL)
        {
            if(l1->val<l2->val)
            {
                a1->next=l1;
                l1=l1->next;
                a1=a1->next;
            }
            else
            {
                a1->next=l2;
                l2=l2->next;
                a1=a1->next;
            }
        }
        if(l1==NULL)
            a1->next=l2;
        if(l2==NULL)
            a1->next=l1;
        return head->next;
        
        
    }
};

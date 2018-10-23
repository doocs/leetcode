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
    ListNode* swapPairs(ListNode* head) {
        if(head==NULL||head->next==NULL)
            return head;
        ListNode *real_head=head->next;
        
        
        ListNode * tw=head->next;
        head->next=head->next->next;
        tw->next=head;
        ListNode *tail=head;
        head=tw->next->next;
        while(head!=NULL&&head->next!=NULL)
        {
            tw=head->next;
            head->next=head->next->next;
            tw->next=head;
            tail->next=tw;
            tail=head;
            head=tw->next->next;
        }
        return real_head;
    }


};




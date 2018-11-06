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
        if(head==NULL||head->next==NULL)//递归的最小情况，链表为空，或者长度为1，直接返回
            return head;
        ListNode *temp=head->next;//定义temp为head->next的node
        ListNode *nextChain=swapPairs(head->next->next);//递归调用得到head->next->next链表交换结果
        head->next=nextChain;//head的next指向递归后的结果
        temp->next=head;//temp也就是之前的head->next节点指向head,那么现在的head为temp
        return temp;
    }
};
        


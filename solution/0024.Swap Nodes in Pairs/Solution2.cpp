// 非递归版本
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
        ListNode *pPre, *p1, *p2 ;
		// 建立一个头结点就不用特殊处理第一个工作节点了
        ListNode node(0) ;
        node.next = head ;
        p2 = &node ;
        
        while ( (pPre = p2) 
               && (p1 = pPre->next) 
               && (p2 = p1->next) )
        {
            pPre->next = p2 ;
            p1->next = p2->next ;
            p2->next = p1 ;
            swap(p1, p2) ; // p1、p2 实质上已经交换了位置，所以指针也要交换一下以保证循环条件顺序
        }
        
        return node.next ;
    }
};
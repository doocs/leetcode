class compare
{
public:
    bool operator()(ListNode *l1,ListNode *l2){
        //if(!l1 || !l2)
          //  return !l1;

        if(l1 == NULL)return 1;
        if(l2 == NULL)return 0;
        return l1->val > l2->val;
        //这里比较的是优先级，默认优先级排序是“<”号，若 l1Val > l2Val 返回真，即表示l1优先级比l2小,l2先入队
        //队列的top()函数指的就是优先级最高的元素，即队头元素
    }
};


class Solution{
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        int len = lists.size();  
        if(len == 0)return NULL;

        priority_queue<ListNode*,vector<ListNode*>,compare> Q;//调用小顶堆的方法构造队列！！！
        
        for(int i = 0;i < len;i++)
        {
            if(lists[i])Q.push(lists[i]);
        }

        ListNode *head = new ListNode(0);
        ListNode *tail = head;
        while(!Q.empty() && Q.top() != NULL)
        {
            ListNode *tmp = Q.top();
            Q.pop();
            tail->next = tmp;
            tail = tail->next;
            Q.push(tmp->next);
        }
        return head->next;
    }
};

-----------------------------------------------------------------------
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
    ListNode* merge(ListNode*l1,ListNode*l2){         //将两个链表合并的函数
        if(l1==NULL&&l2!=NULL)                      //如果两个链表有一个为空，那么返回另外一个。如果都为空，返回NULL。
            return l2;
        else if(l1!=NULL&&l2==NULL)
            return l1;
        else if(l1==NULL&&l2==NULL)
            return NULL;
        
        ListNode*ans,*p;
        if(l1->val<=l2->val){             //处理首节点
            ans=l1;
            l1=l1->next;
        }
        else{
            ans=l2;
            l2=l2->next;
        }
        p=ans;                        
        while(l1!=NULL&&l2!=NULL){       //每次从两个链表中取出一个结点放到结果链表当中
            if(l1->val<=l2->val)
            {
                p->next=l1;
                l1=l1->next;
            }
            else
            {
                p->next=l2;
                l2=l2->next;
            }
            p = p->next;
        }
            
            
        if(l1!=NULL)p->next=l1;           
        if(l2!=NULL)p->next=l2;

        return ans;
    }
    
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        int len = lists.size();
        if(len == 0)return NULL;
        
        ListNode *ans = lists[0];
        for(int i = 1; i < len;i++){
            ans = merge(ans,lists[i]);
        }
        
        return ans;
    }
};
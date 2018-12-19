## 合并K个排序链表
### 题目描述

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

### 解法
从链表数组索引 0 开始，[合并前后相邻两个有序链表](https://github.com/doocs/leetcode/tree/master/solution/021.Merge%20Two%20Sorted%20Lists)，放在后一个链表位置上，依次循环下去...最后 lists[len - 1] 即为合并后的链表。注意处理链表数组元素小于 2 的情况。

--------------------------------
思路1：                     170ms
用第一个链依次和后面的所有链进行双链合并，利用021的双顺序链合并，秒杀！但是效率极低

时间复杂度是O(x(a+b) + (x-1)(a+b+c) + ... + 1 * (a+b+...+z);[a-z]是各链表长度，x表示链表个数-1

可见时间复杂度是极大的


思路2：                      20ms
1.因为链表有序，所以用每个链表的首元素构建初试堆(小顶堆) -- 的队列

2.首元素出队，该元素next指向元素入队

时间复杂度是O(n)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        int len = lists.length;
        if (len == 1) {
            return lists[0];
        }
       
        // 合并前后两个链表，结果放在后一个链表位置上，依次循环下去
        for (int i = 0; i < len - 1; ++i) {
            lists[i + 1] = mergeTwoLists(lists[i], lists[i + 1]);
        }
        return lists[len - 1];
        
    }
    
    /**
     * 合并两个有序链表
     * @param l1 
     * @param l2
     * @return listNode
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```

#### CPP

```C++
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
```

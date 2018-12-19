## 两数相加
### 题目描述

给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

示例：
```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

### 解法
同时遍历两个链表，对应值相加(还有 quotient)求余数得到值并赋给新创建的结点。而商则用quotient存储，供下次相加。

#### Java

初始版本：

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int quotient = 0;
        int t = 0;
        while (l1 != null && l2 != null) {
            t = l1.val + l2.val + quotient;
            quotient = t / 10;
            ListNode node = new ListNode(t % 10);
            cur.next = node;
            l1 = l1.next;
            l2 = l2.next;
            cur = node;
        }
        
        while (l1 != null) {
            t = l1.val + quotient;
            quotient = t / 10;
            ListNode node = new ListNode(t % 10);
            cur.next = node;
            l1 = l1.next;
            cur = node;
        }
        
        while (l2 != null) {
            t = l2.val + quotient;
            quotient = t / 10;
            ListNode node = new ListNode(t % 10);
            cur.next = node;
            l2 = l2.next;
            cur = node;
        }
        
        if (quotient != 0) {
            cur.next = new ListNode(quotient);
            cur = cur.next;
        }
        
        return res.next;
        
    }
}
```

简化版本：
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int quotient = 0;
        while (l1 != null || l2 != null || quotient != 0) {
            int t = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + quotient;
            quotient = t / 10;
            ListNode node = new ListNode(t % 10);
            cur.next = node;
            cur = node;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return res.next;
    }
}
```

#### CPP
```CPP
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {

        ListNode *ans_l = new ListNode(0);
        ListNode *head = ans_l;
        int tmp = 0;
        while(l1 != NULL && l2 != NULL){
            tmp += l1->val + l2->val;
            ans_l->next = new ListNode(tmp % 10);
            tmp = tmp / 10;
            ans_l = ans_l->next;
            l1 = l1->next;
            l2 = l2->next;
        }
        
        while(l1 != NULL){
            tmp += l1->val;
            ans_l->next = new ListNode(tmp % 10);
            tmp = tmp / 10;
            ans_l = ans_l->next;
            l1 = l1->next;  
        }
        
        while(l2 != NULL){
            tmp += l2->val;
            ans_l->next = new ListNode(tmp % 10);
            tmp = tmp / 10;
            ans_l = ans_l->next;
            l2 = l2->next;  
        }
        
        if(tmp)ans_l->next = new ListNode(tmp);

        return head->next;
    }
};

```

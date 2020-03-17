# [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

## 题目描述
<!-- 这里写题目描述 -->
给出两个**非空**的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储**一位**数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

## 解法
<!-- 这里可写通用的实现逻辑 -->
同时遍历两个链表，对应值相加(还有 quotient)求余数得到值并赋给新创建的结点。而商则用 quotient 存储，供下次相加。

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### CPP
```cpp
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

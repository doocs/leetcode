## 环形链表 II
### 题目描述

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 `null`。

**说明：** 不允许修改给定的链表。

**进阶：**
你是否可以不用额外空间解决此题？

### 解法

利用快慢指针，若快指针为 `null`，则不存在环；若快慢指针相遇，则存在环，此时退出循环，利用 `p1` 指向链表头结点，`p2` 指向快慢指针相遇点，随后 `p1`, `p2` 同时前进，相遇时(`p1 == p2`)即为入环的第一个节点。

**证明如下：**

![solution142-slow-fast.png](http://p9ucdlghd.bkt.clouddn.com/solution142-slow-fast.png)

假设链表到入环点距离为 `a`，入环点到快慢指针相遇点距离为 `b`，慢指针行程为`s`，快指针行程是它的 2 倍，即 `2s`。相遇时快指针比慢指针多走了 `n * r` 圈。则：
```
①  a + b = s
②  2s - s = n * r
-> a + b = n * r
-> a = n * r - b
     = (n - 1) * r + r - b     
```
`r - b` 为相遇点到入环点的距离。

`p1`, `p2` 同时向前走 `r - b`，`p2` 到达入环点，而 `p1` 距离入环点还有 `(n - 1) * r`，双方同时走 `(n - 1)` 圈即可相遇，此时相遇点就是入环点!

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (hasCycle) {
            ListNode p1 = head;
            ListNode p2 = slow;
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
        return null;
        
    }
}
```

#### CPP

```C++
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        if(head == NULL)return NULL;
        ListNode *fast = head;
        ListNode *slow = head;
        while(fast != NULL && fast->next != NULL){
            fast = fast->next->next;
            slow = slow->next;
            
            if(fast == slow){
                slow = head;
                while(slow != fast){
                    fast = fast->next;
                    slow = slow->next;
                }
                return fast;
            }
        }
        return NULL;//无环
    }
};

```
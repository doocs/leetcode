# [02.04. Partition List](https://leetcode.cn/problems/partition-list-lcci)

[中文文档](/lcci/02.04.Partition%20List/README.md)

## Description

<p>Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x. If x is contained within the list, the values of x only need to be after the elements less than x (see below). The partition element x can appear anywhere in the &quot;right partition&quot;; it does not need to appear between the left and right partitions.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> head = 3-&gt;5-&gt;8-&gt;5-&gt;10-&gt;2-&gt;1, <em>x</em> = 5

<strong>Output:</strong> 3-&gt;1-&gt;2-&gt;10-&gt;5-&gt;5-&gt;8

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        l1, l2 = ListNode(0), ListNode(0)
        cur1, cur2 = l1, l2
        while head:
            if head.val < x:
                cur1.next = head
                cur1 = cur1.next
            else:
                cur2.next = head
                cur2 = cur2.next
            head = head.next
        cur1.next = l2.next
        cur2.next = None
        return l1.next
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                cur1 = cur1.next;
            } else {
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = head.next;
        }
        cur1.next = l2.next;
        cur2.next = null;
        return l1.next;
    }
}
```

### **C++**

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
        ListNode* l1 = new ListNode();
        ListNode* l2 = new ListNode();
        ListNode* cur1 = l1;
        ListNode* cur2 = l2;
        while (head != nullptr) {
            if (head->val < x) {
                cur1->next = head;
                cur1 = cur1->next;
            } else {
                cur2->next = head;
                cur2 = cur2->next;
            }
            head = head->next;
        }
        cur1->next = l2->next;
        cur2->next = nullptr;
        return l1->next;
    }
};
```

### **TypeScript**

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function partition(head: ListNode | null, x: number): ListNode | null {
    if (head == null) {
        return head;
    }
    let cur = head;
    while (cur.next != null) {
        let node = cur.next;
        if (node.val < x) {
            [head, node.next, cur.next] = [node, head, node.next];
        } else {
            cur = cur.next;
        }
    }
    return head;
}
```

### **...**

```

```

<!-- tabs:end -->

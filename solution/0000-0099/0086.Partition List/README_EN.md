# [86. Partition List](https://leetcode.com/problems/partition-list)

[中文文档](/solution/0000-0099/0086.Partition%20List/README.md)

## Description

<p>Given the <code>head</code> of a linked list and a value <code>x</code>, partition it such that all nodes <strong>less than</strong> <code>x</code> come before nodes <strong>greater than or equal</strong> to <code>x</code>.</p>

<p>You should <strong>preserve</strong> the original relative order of the nodes in each of the two partitions.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0086.Partition%20List/images/partition.jpg" style="width: 662px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,4,3,2,5,2], x = 3
<strong>Output:</strong> [1,2,2,4,3,5]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [2,1], x = 2
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 200]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li><code>-200 &lt;= x &lt;= 200</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        l1, l2 = ListNode(), ListNode()
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
### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} x
 * @return {ListNode}
 */
var partition = function (head, x) {

    if (!head || !head.next) {
        return head;
    }
    const dummy1 = new ListNode(-1), dummy2 = new ListNode(-1);
    let cur1 = dummy1, cur2 = dummy2;
    let cur = head;
    while (cur) {
        if (cur.val < x) {
            cur1.next = cur;
            cur1 = cur1.next;
        } else {
            cur2.next = cur;
            cur2 = cur2.next;
        }
        cur = cur.next;
    }
    cur2.next = null;
    cur1.next = dummy2.next;
    return (dummy1.next);
};
```

### **...**

```

```

<!-- tabs:end -->

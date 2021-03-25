# [82. Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii)

[中文文档](/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/README.md)

## Description

<p>Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only <em>distinct</em> numbers from the original list.</p>

<p>Return the linked list sorted as well.</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 1-&gt;2-&gt;3-&gt;3-&gt;4-&gt;4-&gt;5
<strong>Output:</strong> 1-&gt;2-&gt;5
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> 1-&gt;1-&gt;1-&gt;2-&gt;3
<strong>Output:</strong> 2-&gt;3
</pre>

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
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        dummy = ListNode(-1, head)
        cur = dummy
        while cur.next and cur.next.next:
            if cur.next.val == cur.next.next.val:
                val = cur.next.val
                while cur.next and cur.next.val == val:
                    cur.next = cur.next.next
            else:
                cur = cur.next
        return dummy.next
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* cur = dummy;
        while (cur->next != nullptr && cur->next->next != nullptr) {
            if (cur->next->val == cur->next->next->val) {
                int val = cur->next->val;
                while (cur->next != nullptr && cur->next->val == val) {
                    cur->next = cur->next->next;
                }
            } else {
                cur = cur->next;
            }
        }
        return dummy->next;
    }
};
```

### **...**

```

```

<!-- tabs:end -->

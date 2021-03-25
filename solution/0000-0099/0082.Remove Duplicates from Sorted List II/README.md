# [82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii)

[English Version](/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中&nbsp;<em>没有重复出现&nbsp;</em>的数字。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2-&gt;3-&gt;3-&gt;4-&gt;4-&gt;5
<strong>输出:</strong> 1-&gt;2-&gt;5
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 1-&gt;1-&gt;1-&gt;2-&gt;3
<strong>输出:</strong> 2-&gt;3</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

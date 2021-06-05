# [1836. ](https://leetcode-cn.com/problems/remove-duplicates-from-an-unsorted-linked-list)

[English Version](/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

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
    def deleteDuplicatesUnsorted(self, head: ListNode) -> ListNode:
        cur = head
        counter = collections.Counter()
        while cur:
            counter[cur.val] += 1
            cur = cur.next

        dummy = ListNode(0, head)
        pre, cur = dummy, head
        while cur:
            if counter[cur.val] > 1:
                pre.next = cur.next
            else:
                pre = cur
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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode cur = head;
        Map<Integer, Integer> counter = new HashMap<>();
        for (; cur != null; cur = cur.next) {
            counter.put(cur.val, counter.getOrDefault(cur.val, 0) + 1);
        }

        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (cur = head; cur != null; cur = cur.next) {
            if (counter.get(cur.val) > 1) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

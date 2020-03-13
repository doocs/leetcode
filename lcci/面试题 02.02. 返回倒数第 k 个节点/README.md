# [面试题 02.02. 返回倒数第 k 个节点](https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/)

## 题目描述
实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。

**注意：** 本题相对原题稍作改动

**示例：**

```
输入： 1->2->3->4->5 和 k = 2
输出： 4
```

**说明：**

- 给定的 k 保证是有效的。

## 解法
### Python3
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def kthToLast(self, head: ListNode, k: int) -> int:
        p = q = head
        for _ in range(k):
            q = q.next
        while q:
            q = q.next
            p = p.next
        return p.val
```

### Java
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
    public int kthToLast(ListNode head, int k) {
        ListNode p = head, q = head;
        while (k-- > 0) {
            q = q.next;
        }
        while (q != null) {
            q = q.next;
            p = p.next;
        }
        return p.val;
    }
}
```

### ...
```

```
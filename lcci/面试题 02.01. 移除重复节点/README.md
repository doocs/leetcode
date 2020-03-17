# [面试题 02.01. 移除重复节点](https://leetcode-cn.com/problems/remove-duplicate-node-lcci/)

## 题目描述
<!-- 这里写题目描述 -->
编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。

**示例1:**

```
 输入：[1, 2, 3, 3, 2, 1]
 输出：[1, 2, 3]
```

**示例2:**

```
 输入：[1, 1, 1, 1, 2]
 输出：[1, 2]
```

**提示：**

- 链表长度在[0, 20000]范围内。
- 链表元素在[0, 20000]范围内。

**进阶：**

如果不得使用临时缓冲区，该怎么解决？

## 解法
<!-- 这里可写通用的实现逻辑 -->
使用 set 暂存访问过的元素。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        cache = set()
        cache.add(head.val)
        cur, p = head, head.next
        while p:
            if p.val not in cache:
                cur.next = p
                cur = cur.next
                cache.add(p.val)
            p = p.next
        cur.next = None
        return head
```

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
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> s = new HashSet<>();
        s.add(head.val);
        ListNode p = head.next, cur = head;
        while (p != null) {
            if (!s.contains(p.val)) {
                cur.next = p;
                cur = cur.next;
                s.add(p.val);
            }
            p = p.next;
        }
        cur.next = null;
        return head;

    }
}
```

### ...
```

```

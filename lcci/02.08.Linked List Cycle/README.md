# [面试题 02.08. 环路检测](https://leetcode-cn.com/problems/linked-list-cycle-lcci)

[English Version](/lcci/02.08.Linked%20List%20Cycle/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个有环链表，实现一个算法返回环路的开头节点。<br>有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。</p><br><p><strong>示例 1：</strong><pre><strong>输入：</strong>head = [3,2,0,-4], pos = 1<br><strong>输出：</strong>tail connects to node index 1<br><strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。</pre></p><br><p><strong>示例 2：</strong><pre><strong>输入：</strong>head = [1,2], pos = 0<br><strong>输出：</strong>tail connects to node index 0<br><strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。</pre></p><br><p><strong>示例 3：</strong><pre><strong>输入：</strong>head = [1], pos = -1<br><strong>输出：</strong>no cycle<br><strong>解释：</strong>链表中没有环。</pre></p><br><p><strong>进阶：</strong><br>你是否可以不用额外空间解决此题？</p>

## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        fast = slow = p = head
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
            if fast == slow:
                break
        if fast is None or fast.next is None:
            return None
        while slow != p:
            p = p.next
            slow = slow.next
        return p
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

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
         ListNode fast = head, slow = head;
         while (fast != null && fast.next != null) {
             fast = fast.next.next;
             slow = slow.next;
             if (fast == slow) break;
         }
         if (fast == null || fast.next == null) return null;
         ListNode p = head;
         while (p != slow) {
             p = p.next;
             slow = slow.next;
         }
         return p;
    }
}
```

### ...
```

```

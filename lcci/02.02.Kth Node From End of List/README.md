# [面试题 02.02. 返回倒数第 k 个节点](https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci)

[English Version](/lcci/02.02.Kth%20Node%20From%20End%20of%20List/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。</p>

<p><strong>注意：</strong>本题相对原题稍作改动</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5 和 <em>k</em> = 2
<strong>输出： </strong>4</pre>

<p><strong>说明：</strong></p>

<p>给定的 <em>k</em>&nbsp;保证是有效的。</p>


## 解法
<!-- 这里可写通用的实现逻辑 -->

定义 `p`、`q` 指针指向 `head`。

`p` 先向前走 `k` 步，接着 `p`、`q` 同时向前走，当 `p` 指向 `null` 时，`q` 指向的节点即为链表的倒数第 `k` 个节点。


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

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
            p, q = p.next, q.next
        return p.val
```

### **Java**
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

### **...**
```

```

<!-- tabs:end -->
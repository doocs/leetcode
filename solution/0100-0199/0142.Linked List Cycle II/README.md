# [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii)

[English Version](/solution/0100-0199/0142.Linked%20List%20Cycle%20II/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一个链表，返回链表开始入环的第一个节点。&nbsp;如果链表无环，则返回&nbsp;<code>null</code>。</p>

<p>为了表示给定链表中的环，我们使用整数 <code>pos</code> 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 <code>pos</code> 是 <code>-1</code>，则在该链表中没有环。</p>

<p><strong>说明：</strong>不允许修改给定的链表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>head = [3,2,0,-4], pos = 1
<strong>输出：</strong>tail connects to node index 1
<strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
</pre>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png" style="height: 97px; width: 300px;"></p>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>head = [1,2], pos = 0
<strong>输出：</strong>tail connects to node index 0
<strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
</pre>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" style="height: 74px; width: 141px;"></p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>head = [1], pos = -1
<strong>输出：</strong>no cycle
<strong>解释：</strong>链表中没有环。
</pre>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" style="height: 45px; width: 45px;"></p>

<p>&nbsp;</p>

<p><strong>进阶：</strong><br>
你是否可以不用额外空间解决此题？</p>



## 解法
<!-- 这里可写通用的实现逻辑 -->

先利用快慢指针判断链表是否有环，没有环则直接返回 `null`。

若链表有环，我们分析快慢相遇时走过的距离。

对于慢指针，走过的距离为 `S=X+Y` ①；快指针走过的距离为 `2S=X+Y+N(Y+Z)` ②。如下图所示，其中 `N` 表示快指针与慢指针相遇时在环中所走过的圈数，而我们要求的环入口，也即是 `X` 的距离：

![](./images/linked-list-cycle-ii.png)

我们根据式子①②，得出 `X+Y=N(Y+Z)` => `X=(N-1)(Y+Z)+Z`。

当 `N=1`(快指针在环中走了一圈与慢指针相遇) 时，`X=(1-1)(Y+Z)+Z`，即 `X=Z`。此时只要定义一个 `p` 指针指向头节点，然后慢指针与 `p` 开始同时走，当慢指针与 `p` 相遇时，也就到达了环入口，直接返回 `p` 即可。

当 `N>1`时，也是同样的，说明慢指针除了走 `Z` 步，还需要绕 `N-1` 圈才能与 `p` 相遇。

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
    def detectCycle(self, head: ListNode) -> ListNode:
        slow = fast = head
        has_cycle = False
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
            if slow == fast:
                has_cycle = True
                break
        if not has_cycle:
            return None
        p = head
        while p != slow:
            p, slow = p.next, slow.next
        return p
```

### **Java**
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
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        ListNode p = head;
        while (p != slow) {
            p = p.next;
            slow = slow.next;
        }
        return p;
    }
}
```

### **...**
```

```

<!-- tabs:end -->
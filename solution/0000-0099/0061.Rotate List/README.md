# [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list)

[English Version](/solution/0000-0099/0061.Rotate%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点 <code>head</code> ，旋转链表，将链表每个节点向右移动 <code>k</code><em> </em>个位置。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0061.Rotate%20List/images/rotate1.jpg" style="width: 600px; height: 254px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[4,5,1,2,3]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0061.Rotate%20List/images/roate2.jpg" style="width: 472px; height: 542px;" />
<pre>
<strong>输入：</strong>head = [0,1,2], k = 4
<strong>输出：</strong>[2,0,1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数目在范围 <code>[0, 500]</code> 内</li>
	<li><code>-100 <= Node.val <= 100</code></li>
	<li><code>0 <= k <= 2 * 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将链表右半部分的 k 的节点拼接到 head 即可。

注：k 对链表长度 n 取余，即 `k %= n`。

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
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if k == 0 or head is None or head.next is None:
            return head
        n, cur = 0, head
        while cur:
            n, cur = n + 1, cur.next
        k %= n
        if k == 0:
            return head

        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast.next:
            slow, fast = slow.next, fast.next

        start = slow.next
        slow.next = None
        fast.next = head
        return start
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
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode start = slow.next;
        slow.next = null;
        fast.next = head;
        return start;
    }
}
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

function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if (k == 0 || head == null || head.next == null) return head;
    // mod n
    let n = 0;
    let p = head;
    while (p != null) {
        ++n;
        p = p.next;
    }
    k %= n;
    if (k == 0) return head;

    let fast = head,
        slow = head;
    for (let i = 0; i < k; ++i) {
        fast = fast.next;
    }
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    let start = slow.next;
    slow.next = null;
    fast.next = head;
    return start;
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
    ListNode* rotateRight(ListNode* head, int k) {
        if (k == 0 || !head || !head->next) {
            return head;
        }
        int n = 0;
        for (ListNode *cur = head; !!cur; cur = cur->next) {
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        ListNode *slow = head, *fast = head;
        while (k-- > 0) {
            fast = fast->next;
        }
        while (fast->next) {
            slow = slow->next;
            fast = fast->next;
        }

        ListNode *start = slow->next;
        slow->next = nullptr;
        fast->next = head;
        return start;
    }
};
```

### **C#**

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode RotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null)
        {
            return head;
        }
        var n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next)
        {
            ++n;
        }
        k %= n;
        if (k == 0)
        {
            return head;
        }
        ListNode slow = head, fast = head;
        while (k-- > 0)
        {
            fast = fast.next;
        }
        while (fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode start = slow.next;
        slow.next = null;
        fast.next = head;
        return start;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

# [2074. 反转偶数长度组的节点](https://leetcode.cn/problems/reverse-nodes-in-even-length-groups)

[English Version](/solution/2000-2099/2074.Reverse%20Nodes%20in%20Even%20Length%20Groups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点 <code>head</code> 。</p>

<p>链表中的节点 <strong>按顺序</strong> 划分成若干 <strong>非空</strong> 组，这些非空组的长度构成一个自然数序列（<code>1, 2, 3, 4, ...</code>）。一个组的 <strong>长度</strong> 就是组中分配到的节点数目。换句话说：</p>

<ul>
	<li>节点 <code>1</code> 分配给第一组</li>
	<li>节点 <code>2</code> 和 <code>3</code> 分配给第二组</li>
	<li>节点 <code>4</code>、<code>5</code> 和 <code>6</code> 分配给第三组，以此类推</li>
</ul>

<p>注意，最后一组的长度可能小于或者等于 <code>1 + 倒数第二组的长度</code> 。</p>

<p><strong>反转</strong> 每个 <strong>偶数</strong> 长度组中的节点，并返回修改后链表的头节点 <code>head</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2074.Reverse%20Nodes%20in%20Even%20Length%20Groups/images/eg1.png" style="width: 699px; height: 124px;" /></p>

<pre>
<strong>输入：</strong>head = [5,2,6,3,9,1,7,3,8,4]
<strong>输出：</strong>[5,6,2,3,9,1,4,8,3,7]
<strong>解释：</strong>
- 第一组长度为 1 ，奇数，没有发生反转。
- 第二组长度为 2 ，偶数，节点反转。
- 第三组长度为 3 ，奇数，没有发生反转。
- 最后一组长度为 4 ，偶数，节点反转。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2074.Reverse%20Nodes%20in%20Even%20Length%20Groups/images/eg2.png" style="width: 284px; height: 114px;" /></p>

<pre>
<strong>输入：</strong>head = [1,1,0,6]
<strong>输出：</strong>[1,0,1,6]
<strong>解释：</strong>
- 第一组长度为 1 ，没有发生反转。
- 第二组长度为 2 ，节点反转。
- 最后一组长度为 1 ，没有发生反转。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2074.Reverse%20Nodes%20in%20Even%20Length%20Groups/images/eg3.png" style="width: 139px; height: 114px;" /></p>

<pre>
<strong>输入：</strong>head = [2,1]
<strong>输出：</strong>[2,1]
<strong>解释：</strong>
- 第一组长度为 1 ，没有发生反转。
- 最后一组长度为 1 ，没有发生反转。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点数目范围是 <code>[1, 10<sup>5</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

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
    def reverseEvenLengthGroups(self, head: Optional[ListNode]) -> Optional[ListNode]:
        def reverse(head, l):
            prev, cur, tail = None, head, head
            i = 0
            while cur and i < l:
                t = cur.next
                cur.next = prev
                prev = cur
                cur = t
                i += 1
            tail.next = cur
            return prev

        n = 0
        t = head
        while t:
            t = t.next
            n += 1
        dummy = ListNode(0, head)
        prev = dummy
        l = 1
        while (1 + l) * l // 2 <= n and prev:
            if l % 2 == 0:
                prev.next = reverse(prev.next, l)
            i = 0
            while i < l and prev:
                prev = prev.next
                i += 1
            l += 1
        left = n - l * (l - 1) // 2
        if left > 0 and left % 2 == 0:
            prev.next = reverse(prev.next, left)
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
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int n = 0;
        for (ListNode t = head; t != null; t = t.next) {
            ++n;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        int l = 1;
        for (; (1 + l) * l / 2 <= n && prev != null; ++l) {
            if (l % 2 == 0) {
                ListNode node = prev.next;
                prev.next = reverse(node, l);
            }
            for (int i = 0; i < l && prev != null; ++i) {
                prev = prev.next;
            }
        }
        int left = n - l * (l - 1) / 2;
        if (left > 0 && left % 2 == 0) {
            ListNode node = prev.next;
            prev.next = reverse(node, left);
        }
        return dummy.next;

    }

    private ListNode reverse(ListNode head, int l) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode tail = cur;
        int i = 0;
        while (cur != null && i < l) {
            ListNode t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
            ++i;
        }
        tail.next = cur;
        return prev;
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

function reverseEvenLengthGroups(head: ListNode | null): ListNode | null {
    let nums = [];
    let cur = head;
    while (cur) {
        nums.push(cur.val);
        cur = cur.next;
    }

    const n = nums.length;
    for (let i = 0, k = 1; i < n; i += k, k++) {
        // 最后一组， 可能出现不足
        k = Math.min(n - i, k);
        if (!(k & 1)) {
            let tmp = nums.splice(i, k);
            tmp.reverse();
            nums.splice(i, 0, ...tmp);
        }
    }

    cur = head;
    for (let num of nums) {
        cur.val = num;
        cur = cur.next;
    }
    return head;
}
```

### **...**

```

```

<!-- tabs:end -->

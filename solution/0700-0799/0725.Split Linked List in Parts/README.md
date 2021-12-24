# [725. 分隔链表](https://leetcode-cn.com/problems/split-linked-list-in-parts)

[English Version](/solution/0700-0799/0725.Split%20Linked%20List%20in%20Parts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个头结点为 <code>root</code> 的链表, 编写一个函数以将链表分隔为 <code>k</code> 个连续的部分。</p>

<p>每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。</p>

<p>这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。</p>

<p>返回一个符合上述规则的链表的列表。</p>

<p>举例： 1-&gt;2-&gt;3-&gt;4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> 
root = [1, 2, 3], k = 5
<strong>输出:</strong> [[1],[2],[3],[],[]]
<strong>解释:</strong>
输入输出各部分都应该是链表，而不是数组。
例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> 
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
<strong>输出:</strong> [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
<strong>解释:</strong>
输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>root</code> 的长度范围：&nbsp;<code>[0, 1000]</code>.</li>
	<li>输入的每个节点的大小范围：<code>[0, 999]</code>.</li>
	<li><code>k</code>&nbsp;的取值范围：&nbsp;<code>[1, 50]</code>.</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先遍历链表，统计结点总个数。

接着将链表拆分，`width` 表示每一部分至少含有的结点个数，而 `remainder` 表示前 `remainder` 部分，每一部分多出一个数。

然后遍历链表，依次拆出每一部分，添加到结果数组 `res` 即可。

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
    def splitListToParts(self, root: ListNode, k: int) -> List[ListNode]:
        n, cur = 0, root
        while cur:
            n += 1
            cur = cur.next
        cur = root
        width, remainder = divmod(n, k)
        res = [None for _ in range(k)]
        for i in range(k):
            head = cur
            for j in range(width + (i < remainder) - 1):
                if cur:
                    cur = cur.next
            if cur:
                cur.next, cur = None, cur.next
            res[i] = head
        return res
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
    public ListNode[] splitListToParts(ListNode root, int k) {
        int n = 0;
        ListNode cur = root;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        // width 表示每一部分至少含有的结点个数
        // remainder 表示前 remainder 部分，每一部分多出一个数
        int width = n / k, remainder = n % k;
        ListNode[] res = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = cur;
            for (int j = 0; j < width + ((i < remainder) ? 1 : 0) - 1; ++j) {
                if (cur != null) {
                    cur = cur.next;
                }
            }
            if (cur != null) {
                ListNode t = cur.next;
                cur.next = null;
                cur = t;
            }
            res[i] = head;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

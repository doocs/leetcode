# [817. 链表组件](https://leetcode-cn.com/problems/linked-list-components)

[English Version](/solution/0800-0899/0817.Linked%20List%20Components/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定链表头结点&nbsp;<code>head</code>，该链表上的每个结点都有一个 <strong>唯一的整型值</strong> 。</p>

<p>同时给定列表&nbsp;<code>G</code>，该列表是上述链表中整型值的一个子集。</p>

<p>返回列表&nbsp;<code>G</code>&nbsp;中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表&nbsp;<code>G</code>&nbsp;中）构成的集合。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre><strong>输入:</strong> 
head: 0-&gt;1-&gt;2-&gt;3
G = [0, 1, 3]
<strong>输出:</strong> 2
<strong>解释:</strong> 
链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> 
head: 0-&gt;1-&gt;2-&gt;3-&gt;4
G = [0, 3, 1, 4]
<strong>输出:</strong> 2
<strong>解释:</strong> 
链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>如果&nbsp;<code>N</code>&nbsp;是给定链表&nbsp;<code>head</code>&nbsp;的长度，<code>1 &lt;= N &lt;= 10000</code>。</li>
	<li>链表中每个结点的值所在范围为&nbsp;<code>[0, N - 1]</code>。</li>
	<li><code>1 &lt;= G.length &lt;= 10000</code></li>
	<li><code>G</code> 是链表中所有结点的值的一个子集.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

定义 pre 表示是否可加 1，初始为 true。

遍历链表各个结点：

- 若当前结点值在 nums 中，并且当前为可加 1 的状态，则 `res++`，并且将 pre 状态置为 false；
- 若当前结点值不在 nums 中，则将 pre 置为 true，表示可加 1。

最后返回 res 即可。

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
    def numComponents(self, head: ListNode, nums: List[int]) -> int:
        s = set(nums)
        res, pre = 0, True
        while head:
            if head.val in s:
                if pre:
                    res += 1
                    pre = False
            else:
                pre = True
            head = head.next
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
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        int res = 0;
        boolean pre = true;
        while (head != null) {
            if (s.contains(head.val)) {
                if (pre) {
                    ++res;
                    pre = false;
                }
            } else {
                pre = true;
            }
            head = head.next;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

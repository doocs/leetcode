# [1019. 链表中的下一个更大节点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list)

[English Version](/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个以头节点&nbsp;<code>head</code>&nbsp;作为第一个节点的链表。链表中的节点分别编号为：<code>node_1, node_2, node_3, ...</code> 。</p>

<p>每个节点都可能有下一个更大值（<em>next larger</em> <strong>value</strong>）：对于&nbsp;<code>node_i</code>，如果其&nbsp;<code>next_larger(node_i)</code>&nbsp;是&nbsp;<code>node_j.val</code>，那么就有&nbsp;<code>j &gt; i</code>&nbsp;且&nbsp;&nbsp;<code>node_j.val &gt; node_i.val</code>，而&nbsp;<code>j</code>&nbsp;是可能的选项中最小的那个。如果不存在这样的&nbsp;<code>j</code>，那么下一个更大值为&nbsp;<code>0</code>&nbsp;。</p>

<p>返回整数答案数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[i] = next_larger(node_{i+1})</code>&nbsp;。</p>

<p><strong><em>注意：</em></strong>在下面的示例中，诸如 <code>[2,1,5]</code> 这样的<strong>输入</strong>（不是输出）是链表的序列化表示，其头节点的值为&nbsp;2，第二个节点值为 1，第三个节点值为&nbsp;5 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[2,1,5]
<strong>输出：</strong>[5,5,0]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[2,7,4,3,5]
<strong>输出：</strong>[7,0,5,5,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[1,7,5,1,9,2,5,1]
<strong>输出：</strong>[7,9,9,9,0,5,0,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>对于链表中的每个节点，<code>1 &lt;= node.val&nbsp;&lt;= 10^9</code></li>
	<li>给定列表的长度在 <code>[0, 10000]</code>&nbsp;范围内</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“单调栈”实现。

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
    def nextLargerNodes(self, head: ListNode) -> List[int]:
        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        s = []
        larger = [0] * len(nums)
        for i, num in enumerate(nums):
            while s and nums[s[-1]] < num:
                larger[s.pop()] = num
            s.append(i)
        return larger
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
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        Deque<Integer> s = new ArrayDeque<>();
        int[] larger = new int[nums.size()];
        for (int i = 0; i < nums.size(); ++i) {
            while (!s.isEmpty() && nums.get(s.peek()) < nums.get(i)) {
                larger[s.pop()] = nums.get(i);
            }
            s.push(i);
        }
        return larger;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {number[]}
 */
var nextLargerNodes = function (head) {
    let nums = [];
    while (head != null) {
        nums.push(head.val);
        head = head.next;
    }
    const n = nums.length;
    let larger = new Array(n).fill(0);
    let stack = [];
    for (let i = 0; i < n; i++) {
        let num = nums[i];
        while (stack.length > 0 && nums[stack[stack.length - 1]] < num) {
            larger[stack.pop()] = num;
        }
        stack.push(i);
    }
    return larger;
};
```

### **...**

```

```

<!-- tabs:end -->

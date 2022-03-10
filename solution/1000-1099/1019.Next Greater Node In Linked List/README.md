# [1019. 链表中的下一个更大节点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list)

[English Version](/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的链表&nbsp;<code>head</code></p>

<p>对于列表中的每个节点，查找下一个 <strong>更大节点</strong> 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 <strong>严格大于</strong> 它的值。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer[i]</code> 是第 <code>i</code> 个节点( <strong>从1开始</strong> )的下一个更大的节点的值。如果第 <code>i</code> 个节点没有下一个更大的节点，设置&nbsp;<code>answer[i] = 0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/images/linkedlistnext1.jpg" /></p>

<pre>
<strong>输入：</strong>head = [2,1,5]
<strong>输出：</strong>[5,5,0]
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/images/linkedlistnext2.jpg" /></p>

<pre>
<strong>输入：</strong>head = [2,7,4,3,5]
<strong>输出：</strong>[7,0,5,5,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点数为&nbsp;<code>n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
</ul>

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

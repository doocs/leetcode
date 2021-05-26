# [1019. Next Greater Node In Linked List](https://leetcode.com/problems/next-greater-node-in-linked-list)

[中文文档](/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/README.md)

## Description

<p>We are given a linked list with&nbsp;<code>head</code>&nbsp;as the first node.&nbsp; Let&#39;s number the&nbsp;nodes in the list: <code>node_1, node_2, node_3, ...</code> etc.</p>

<p>Each node may have a <em>next larger</em> <strong>value</strong>: for <code>node_i</code>,&nbsp;<code>next_larger(node_i)</code>&nbsp;is the <code>node_j.val</code> such that <code>j &gt; i</code>, <code>node_j.val &gt; node_i.val</code>, and <code>j</code> is the smallest possible choice.&nbsp; If such a <code>j</code>&nbsp;does not exist, the next larger value is <code>0</code>.</p>

<p>Return an array of integers&nbsp;<code>answer</code>, where <code>answer[i] = next_larger(node_{i+1})</code>.</p>

<p>Note that in the example <strong>inputs</strong>&nbsp;(not outputs) below, arrays such as <code>[2,1,5]</code>&nbsp;represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[2,1,5]</span>

<strong>Output: </strong><span id="example-output-1">[5,5,0]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[2,7,4,3,5]</span>

<strong>Output: </strong><span id="example-output-2">[7,0,5,5,0]</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">[1,7,5,1,9,2,5,1]</span>

<strong>Output: </strong><span id="example-output-3">[7,9,9,9,0,5,0,0]</span>

</pre>

<p>&nbsp;</p>

<p><strong><span>Note:</span></strong></p>

<ol>
	<li><code><span>1 &lt;= node.val&nbsp;&lt;= 10^9</span></code><span>&nbsp;for each node in the linked list.</span></li>
	<li>The given list has length in the range <code>[0, 10000]</code>.</li>
</ol>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

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
var nextLargerNodes = function(head) {
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

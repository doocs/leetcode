# [817. Linked List Components](https://leetcode.com/problems/linked-list-components)

[中文文档](/solution/0800-0899/0817.Linked%20List%20Components/README.md)

## Description

<p>We are given&nbsp;<code>head</code>,&nbsp;the head node of a linked list containing&nbsp;<strong>unique integer values</strong>.</p>

<p>We are also given the list&nbsp;<code>G</code>, a subset of the values in the linked list.</p>

<p>Return the number of connected components in <code>G</code>, where two values are connected if they appear consecutively in the linked list.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> 

head: 0-&gt;1-&gt;2-&gt;3

G = [0, 1, 3]

<strong>Output:</strong> 2

<strong>Explanation:</strong> 

0 and 1 are connected, so [0, 1] and [3] are the two connected components.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> 

head: 0-&gt;1-&gt;2-&gt;3-&gt;4

G = [0, 3, 1, 4]

<strong>Output:</strong> 2

<strong>Explanation:</strong> 

0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li>If&nbsp;<code>N</code>&nbsp;is the&nbsp;length of the linked list given by&nbsp;<code>head</code>,&nbsp;<code>1 &lt;= N &lt;= 10000</code>.</li>
	<li>The value of each node in the linked list will be in the range<code> [0, N - 1]</code>.</li>
	<li><code>1 &lt;= G.length &lt;= 10000</code>.</li>
	<li><code>G</code> is a subset of all values in the linked list.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

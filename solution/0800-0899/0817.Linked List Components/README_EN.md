# [817. Linked List Components](https://leetcode.com/problems/linked-list-components)

[中文文档](/solution/0800-0899/0817.Linked%20List%20Components/README.md)

## Description

<p>You are given the <code>head</code> of a linked list containing unique integer values and an integer array <code>nums</code> that is a subset of the linked list values.</p>

<p>Return <em>the number of connected components in </em><code>nums</code><em> where two values are connected if they appear <strong>consecutively</strong> in the linked list</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0817.Linked%20List%20Components/images/lc-linkedlistcom1.jpg" style="width: 424px; height: 65px;" />
<pre>
<strong>Input:</strong> head = [0,1,2,3], nums = [0,1,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0817.Linked%20List%20Components/images/lc-linkedlistcom2.jpg" style="width: 544px; height: 65px;" />
<pre>
<strong>Input:</strong> head = [0,1,2,3,4], nums = [0,3,1,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the linked list is <code>n</code>.</li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= Node.val &lt; n</code></li>
	<li>All the values <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= nums.length &lt;= n</code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
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

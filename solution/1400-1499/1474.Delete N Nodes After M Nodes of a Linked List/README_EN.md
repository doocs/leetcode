# [1474. Delete N Nodes After M Nodes of a Linked List](https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list)

[中文文档](/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/README.md)

## Description

<p>You are given the <code>head</code> of a linked list and two integers <code>m</code> and <code>n</code>.</p>

<p>Traverse the linked list and remove some nodes in the following way:</p>

<ul>
	<li>Start with the head as the current node.</li>
	<li>Keep the first <code>m</code> nodes starting with the current node.</li>
	<li>Remove the next <code>n</code> nodes</li>
	<li>Keep repeating steps 2 and 3 until you reach the end of the list.</li>
</ul>

<p>Return <em>the head of the modified list after removing the mentioned nodes</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/images/sample_1_1848.png" style="width: 600px; height: 95px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5,6,7,8,9,10,11,12,13], m = 2, n = 3
<strong>Output:</strong> [1,2,6,7,11,12]
<strong>Explanation:</strong> Keep the first (m = 2) nodes starting from the head of the linked List  (1 -&gt;2) show in black nodes.
Delete the next (n = 3) nodes (3 -&gt; 4 -&gt; 5) show in read nodes.
Continue with the same procedure until reaching the tail of the Linked List.
Head of the linked list after removing nodes is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/images/sample_2_1848.png" style="width: 600px; height: 123px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5,6,7,8,9,10,11], m = 1, n = 3
<strong>Output:</strong> [1,5,9]
<strong>Explanation:</strong> Head of linked list after removing nodes is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve this problem by modifying the list in-place?</p>

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
    def deleteNodes(self, head: ListNode, m: int, n: int) -> ListNode:
        pre = head
        while pre:
            for _ in range(m - 1):
                if pre:
                    pre = pre.next
            if pre is None:
                return head
            cur = pre
            for _ in range(n):
                if cur:
                    cur = cur.next
            pre.next = None if cur is None else cur.next
            pre = pre.next
        return head
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
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode pre = head;
        while (pre != null) {
            for (int i = 0; i < m - 1 && pre != null; ++i) {
                pre = pre.next;
            }
            if (pre == null) {
                return head;
            }
            ListNode cur = pre;
            for (int i = 0; i < n && cur != null; ++i) {
                cur = cur.next;
            }
            pre.next = cur == null ? null : cur.next;
            pre = pre.next;
        }
        return head;
    }
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
    ListNode* deleteNodes(ListNode* head, int m, int n) {
        auto pre = head;
        while (pre) {
            for (int i = 0; i < m - 1 && pre; ++i) {
                pre = pre->next;
            }
            if (!pre) {
                return head;
            }
            auto cur = pre;
            for (int i = 0; i < n && cur; ++i) {
                cur = cur->next;
            }
            pre->next = cur ? cur->next : nullptr;
            pre = pre->next;
        }
        return head;
    }
};
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNodes(head *ListNode, m int, n int) *ListNode {
	pre := head
	for pre != nil {
		for i := 0; i < m-1 && pre != nil; i++ {
			pre = pre.Next
		}
		if pre == nil {
			return head
		}
		cur := pre
		for i := 0; i < n && cur != nil; i++ {
			cur = cur.Next
		}
		pre.Next = nil
		if cur != nil {
			pre.Next = cur.Next
		}
		pre = pre.Next
	}
	return head
}
```

### **...**

```

```

<!-- tabs:end -->

# [02.02. Kth Node From End of List](https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci)

## Description
<p>Implement an algorithm to find the kth to last element of a singly linked list.&nbsp;Return the value of the element.</p>



<p><strong>Note: </strong>This problem is slightly different from the original one in the book.</p>



<p><strong>Example: </strong></p>



<pre>

<strong>Input: </strong> 1-&gt;2-&gt;3-&gt;4-&gt;5 和 <em>k</em> = 2

<strong>Output:  </strong>4</pre>



<p><strong>Note: </strong></p>

<p>k is always valid.</p>


## Solutions


### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def kthToLast(self, head: ListNode, k: int) -> int:
        p = q = head
        for _ in range(k):
            q = q.next
        while q:
            q = q.next
            p = p.next
        return p.val
```

### Java

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
    public int kthToLast(ListNode head, int k) {
        ListNode p = head, q = head;
        while (k-- > 0) {
            q = q.next;
        }
        while (q != null) {
            q = q.next;
            p = p.next;
        }
        return p.val;
    }
}
```

### ...
```

```

---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.03.Delete%20Middle%20Node/README_EN.md
---

<!-- problem:start -->

# [02.03. Delete Middle Node](https://leetcode.cn/problems/delete-middle-node-lcci)

[中文文档](/lcci/02.03.Delete%20Middle%20Node/README.md)

## Description

<!-- description:start -->

<p>Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list, given only access to that node.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>the node c from the linked list a-&gt;b-&gt;c-&gt;d-&gt;e-&gt;f

<strong>Output: </strong>nothing is returned, but the new linked list looks like a-&gt;b-&gt;d-&gt;e-&gt;f

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Node Assignment

We can replace the value of the current node with the value of the next node, and then delete the next node. This way, we can achieve the purpose of deleting the current node.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def deleteNode(self, node):
        node.val = node.next.val
        node.next = node.next.next
```

#### Java

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
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```

#### C++

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void deleteNode(ListNode* node) {
        node->val = node->next->val;
        node->next = node->next->next;
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNode(node *ListNode) {
	node.Val = node.Next.Val
	node.Next = node.Next.Next
}
```

#### JavaScript

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} node
 * @return {void} Do not return anything, modify node in-place instead.
 */
var deleteNode = function (node) {
    node.val = node.next.val;
    node.next = node.next.next;
};
```

#### Swift

```swift
/**
*    public class ListNode {
*        var val: Int
*        var next: ListNode?
*        init(_ x: Int) {
*            self.val = x
*            self.next = nil
*        }
*    }
*/
class Solution {
    func deleteNode(_ node: ListNode?) {
        guard let node = node, let next = node.next else { return }
        node.val = next.val
        node.next = next.next
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

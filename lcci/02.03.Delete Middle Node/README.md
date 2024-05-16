---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.03.Delete%20Middle%20Node/README.md
---

<!-- problem:start -->

# [面试题 02.03. 删除中间节点](https://leetcode.cn/problems/delete-middle-node-lcci)

[English Version](/lcci/02.03.Delete%20Middle%20Node/README_EN.md)

## 题目描述

<!-- description:start -->

<p>若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。</p>

<p>假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。</p>

<p>例如，传入节点 <code>c</code>（位于单向链表 <code>a->b->c->d->e->f</code> 中），将其删除后，剩余链表为 <code>a->b->d->e->f</code></p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>节点 5 （位于单向链表 4->5->1->9 中）
<strong>输出：</strong>不返回任何数据，从链表中删除传入的节点 5，使链表变为 4->1->9
</pre>

<p> </p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：节点赋值

我们可以将当前节点的值替换为下一个节点的值，然后删除下一个节点。这样就可以达到删除当前节点的目的。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

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

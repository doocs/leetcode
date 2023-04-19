# [237. 删除链表中的节点](https://leetcode.cn/problems/delete-node-in-a-linked-list)

[English Version](/solution/0200-0299/0237.Delete%20Node%20in%20a%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个单链表的&nbsp;<code>head</code>，我们想删除它其中的一个节点&nbsp;<code>node</code>。</p>

<p>给你一个需要删除的节点&nbsp;<code>node</code>&nbsp;。你将&nbsp;<strong>无法访问</strong>&nbsp;第一个节点&nbsp;&nbsp;<code>head</code>。</p>

<p>链表的所有值都是 <b>唯一的</b>，并且保证给定的节点&nbsp;<code>node</code>&nbsp;不是链表中的最后一个节点。</p>

<p>删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：</p>

<ul>
	<li>给定节点的值不应该存在于链表中。</li>
	<li>链表中的节点数应该减少 1。</li>
	<li><code>node</code>&nbsp;前面的所有值顺序相同。</li>
	<li><code>node</code>&nbsp;后面的所有值顺序相同。</li>
</ul>

<p><strong>自定义测试：</strong></p>

<ul>
	<li>对于输入，你应该提供整个链表&nbsp;<code>head</code>&nbsp;和要给出的节点&nbsp;<code>node</code>。<code>node</code>&nbsp;不应该是链表的最后一个节点，而应该是链表中的一个实际节点。</li>
	<li>我们将构建链表，并将节点传递给你的函数。</li>
	<li>输出将是调用你函数后的整个链表。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0237.Delete%20Node%20in%20a%20Linked%20List/images/node1.jpg" style="height: 286px; width: 400px;" />
<pre>
<strong>输入：</strong>head = [4,5,1,9], node = 5
<strong>输出：</strong>[4,1,9]
<strong>解释：</strong>指定链表中值为&nbsp;5&nbsp;的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 1 -&gt; 9
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0237.Delete%20Node%20in%20a%20Linked%20List/images/node2.jpg" style="height: 315px; width: 400px;" />
<pre>
<strong>输入：</strong>head = [4,5,1,9], node = 1
<strong>输出：</strong>[4,5,9]
<strong>解释：</strong>指定链表中值为&nbsp;1&nbsp;的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 5 -&gt; 9</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数目范围是 <code>[2, 1000]</code></li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
	<li>链表中每个节点的值都是 <strong>唯一</strong> 的</li>
	<li>需要删除的节点 <code>node</code> 是 <strong>链表中的节点</strong> ，且 <strong>不是末尾节点</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：节点赋值**

我们可以将当前节点的值替换为下一个节点的值，然后删除下一个节点。这样就可以达到删除当前节点的目的。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

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
    def deleteNode(self, node):
        """
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        node.val = node.next.val
        node.next = node.next.next
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
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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

### **Go**

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

### **TypeScript**

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

/**
 Do not return anything, modify it in-place instead.
 */
function deleteNode(node: ListNode | null): void {
    node.val = node.next.val;
    node.next = node.next.next;
}
```

### **C#**

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void DeleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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
 * @param {ListNode} node
 * @return {void} Do not return anything, modify node in-place instead.
 */
var deleteNode = function (node) {
    node.val = node.next.val;
    node.next = node.next.next;
};
```

### **...**

```

```

<!-- tabs:end -->

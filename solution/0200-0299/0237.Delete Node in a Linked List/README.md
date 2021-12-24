# [237. 删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list)

[English Version](/solution/0200-0299/0237.Delete%20Node%20in%20a%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 <strong>要被删除的节点</strong> 。</p>

<p>&nbsp;</p>

<p>现有一个链表 --&nbsp;head =&nbsp;[4,5,1,9]，它可以表示为:</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0237.Delete%20Node%20in%20a%20Linked%20List/images/237_example.png" style="height: 49px; width: 300px;"></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>head = [4,5,1,9], node = 5
<strong>输出：</strong>[4,1,9]
<strong>解释：</strong>给定你链表中值为&nbsp;5&nbsp;的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 1 -&gt; 9.
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>head = [4,5,1,9], node = 1
<strong>输出：</strong>[4,5,9]
<strong>解释：</strong>给定你链表中值为&nbsp;1&nbsp;的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 5 -&gt; 9.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表至少包含两个节点。</li>
	<li>链表中所有节点的值都是唯一的。</li>
	<li>给定的节点为非末尾节点并且一定是链表中的一个有效节点。</li>
	<li>不要从你的函数中返回任何结果。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将 `node.next` 节点的值赋给 `node`，然后将 `node.next` 指向 `node.next` 的下一个节点。

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

<!-- tabs:end -->

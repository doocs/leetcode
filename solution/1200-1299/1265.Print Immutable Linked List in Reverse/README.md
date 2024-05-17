---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1265.Print%20Immutable%20Linked%20List%20in%20Reverse/README.md
tags:
    - 栈
    - 递归
    - 链表
    - 双指针
---

<!-- problem:start -->

# [1265. 逆序打印不可变链表 🔒](https://leetcode.cn/problems/print-immutable-linked-list-in-reverse)

[English Version](/solution/1200-1299/1265.Print%20Immutable%20Linked%20List%20in%20Reverse/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给您一个不可变的链表，使用下列接口逆序打印每个节点的值：</p>

<ul>
	<li><code>ImmutableListNode</code>: 描述不可变链表的接口，链表的头节点已给出。</li>
</ul>

<p>您需要使用以下函数来访问此链表（您&nbsp;<strong>不能&nbsp;</strong>直接访问&nbsp;<code>ImmutableListNode</code>）：</p>

<ul>
	<li><code>ImmutableListNode.printValue()</code>：打印当前节点的值。</li>
	<li><code>ImmutableListNode.getNext()</code>：返回下一个节点。</li>
</ul>

<p>输入只用来内部初始化链表。您不可以通过修改链表解决问题。也就是说，您只能通过上述 API 来操作链表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4]
<strong>输出：</strong>[4,3,2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [0,-4,-1,3,-5]
<strong>输出：</strong>[-5,3,-1,-4,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [-2,0,6,4,4,-6]
<strong>输出：</strong>[-6,4,4,6,0,-2]
</pre>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表的长度在&nbsp;<code>[1, 1000]</code>&nbsp;之间。</li>
	<li>每个节点的值在&nbsp;<code>[-1000, 1000]</code>&nbsp;之间。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>您是否可以：</p>

<ul>
	<li>使用常数级空间复杂度解决问题？</li>
	<li>使用线性级时间复杂度和低于线性级空间复杂度解决问题？</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们可以使用递归来实现链表的逆序打印。在函数中，我们判断当前节点是否为空，如果不为空，则获取下一个节点，然后递归调用函数本身，最后打印当前节点的值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是链表的长度。

<!-- tabs:start -->

```python
# """
# This is the ImmutableListNode's API interface.
# You should not implement it, or speculate about its implementation.
# """
# class ImmutableListNode:
#     def printValue(self) -> None: # print the value of this node.
#     def getNext(self) -> 'ImmutableListNode': # return the next node.


class Solution:
    def printLinkedListInReverse(self, head: 'ImmutableListNode') -> None:
        if head:
            self.printLinkedListInReverse(head.getNext())
            head.printValue()
```

```java
/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * interface ImmutableListNode {
 *     public void printValue(); // print the value of this node.
 *     public ImmutableListNode getNext(); // return the next node.
 * };
 */

class Solution {
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head != null) {
            printLinkedListInReverse(head.getNext());
            head.printValue();
        }
    }
}
```

```cpp
/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * class ImmutableListNode {
 * public:
 *    void printValue(); // print the value of the node.
 *    ImmutableListNode* getNext(); // return the next node.
 * };
 */

class Solution {
public:
    void printLinkedListInReverse(ImmutableListNode* head) {
        if (head) {
            printLinkedListInReverse(head->getNext());
            head->printValue();
        }
    }
};
```

```go
/*   Below is the interface for ImmutableListNode, which is already defined for you.
 *
 *   type ImmutableListNode struct {
 *
 *   }
 *
 *   func (this *ImmutableListNode) getNext() ImmutableListNode {
 *		// return the next node.
 *   }
 *
 *   func (this *ImmutableListNode) printValue() {
 *		// print the value of this node.
 *   }
 */

func printLinkedListInReverse(head ImmutableListNode) {
	if head != nil {
		printLinkedListInReverse(head.getNext())
		head.printValue()
	}
}
```

```ts
/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ImmutableListNode {
 *      printValue() {}
 *
 *      getNext(): ImmutableListNode {}
 * }
 */

function printLinkedListInReverse(head: ImmutableListNode) {
    if (head) {
        printLinkedListInReverse(head.next);
        head.printValue();
    }
}
```

```cs
/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * class ImmutableListNode {
 *     public void PrintValue(); // print the value of this node.
 *     public ImmutableListNode GetNext(); // return the next node.
 * }
 */

public class Solution {
    public void PrintLinkedListInReverse(ImmutableListNode head) {
        if (head != null) {
            PrintLinkedListInReverse(head.GetNext());
            head.PrintValue();
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

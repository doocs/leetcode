# [1265. Print Immutable Linked List in Reverse](https://leetcode.com/problems/print-immutable-linked-list-in-reverse)

[中文文档](/solution/1200-1299/1265.Print%20Immutable%20Linked%20List%20in%20Reverse/README.md)

## Description

<p>You are given an immutable linked list, print out all values of each node in reverse with the help of the following&nbsp;interface:</p>

<ul>
	<li><code>ImmutableListNode</code>:&nbsp;An interface of immutable linked list, you are given the head of the list.</li>
</ul>

<p>You need to use the following functions to access the linked list (you <strong>can&#39;t</strong> access the <code>ImmutableListNode</code> directly):</p>

<ul>
	<li><code>ImmutableListNode.printValue()</code>: Print value of the current node.</li>
	<li><code>ImmutableListNode.getNext()</code>: Return the next node.</li>
</ul>

<p>The input is only given to initialize the linked list internally.&nbsp;You must solve this problem without modifying the linked list. In other words, you must operate&nbsp;the linked list using only the mentioned&nbsp;APIs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2,3,4]
<strong>Output:</strong> [4,3,2,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [0,-4,-1,3,-5]
<strong>Output:</strong> [-5,3,-1,-4,0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [-2,0,6,4,4,-6]
<strong>Output:</strong> [-6,4,4,6,0,-2]
</pre>

<ul>
</ul>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The length of the linked list&nbsp;is between <code>[1, 1000]</code>.</li>
	<li>The value of each&nbsp;node in the linked list&nbsp;is between <code>[-1000, 1000]</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Follow up:</strong></p>

<p>Could you solve this problem in:</p>

<ul>
	<li>Constant space complexity?</li>
	<li>Linear time complexity and less than linear space complexity?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->

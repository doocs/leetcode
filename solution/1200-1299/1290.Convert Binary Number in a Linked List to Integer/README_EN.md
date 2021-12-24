# [1290. Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer)

[中文文档](/solution/1200-1299/1290.Convert%20Binary%20Number%20in%20a%20Linked%20List%20to%20Integer/README.md)

## Description

<p>Given <code>head</code> which is a reference node to&nbsp;a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.</p>

<p>Return the <em>decimal value</em> of the number in the linked list.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1290.Convert%20Binary%20Number%20in%20a%20Linked%20List%20to%20Integer/images/graph-1.png" style="width: 426px; height: 108px;" />

<pre>

<strong>Input:</strong> head = [1,0,1]

<strong>Output:</strong> 5

<strong>Explanation:</strong> (101) in base 2 = (5) in base 10

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> head = [0]

<strong>Output:</strong> 0

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> head = [1]

<strong>Output:</strong> 1

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]

<strong>Output:</strong> 18880

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> head = [0,0]

<strong>Output:</strong> 0

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The Linked List is not empty.</li>
	<li>Number of nodes&nbsp;will not exceed <code>30</code>.</li>
	<li>Each node&#39;s value is either&nbsp;<code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getDecimalValue(self, head: ListNode) -> int:
        res = 0
        while head:
            res = (res << 1) + head.val
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
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = (res << 1) + head.val;
            head = head.next;
        }
        return res;
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
 * @param {ListNode} head
 * @return {number}
 */
var getDecimalValue = function (head) {
    let res = 0;
    while (head != null) {
        res = (res << 1) + head.val;
        head = head.next;
    }
    return res;
};
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
    int getDecimalValue(ListNode* head) {
        int res = 0;
        while (head != NULL) {
            res = (res << 1) + head->val;
            head = head->next;
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->

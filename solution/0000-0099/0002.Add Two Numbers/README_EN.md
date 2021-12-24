# [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers)

[中文文档](/solution/0000-0099/0002.Add%20Two%20Numbers/README.md)

## Description

<p>You are given two <strong>non-empty</strong> linked lists representing two non-negative integers. The digits are stored in <strong>reverse order</strong>, and each of their nodes contains a single digit. Add the two numbers and return the sum&nbsp;as a linked list.</p>

<p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0002.Add%20Two%20Numbers/images/addtwonumber1.jpg" style="width: 483px; height: 342px;" />
<pre>
<strong>Input:</strong> l1 = [2,4,3], l2 = [5,6,4]
<strong>Output:</strong> [7,0,8]
<strong>Explanation:</strong> 342 + 465 = 807.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> l1 = [0], l2 = [0]
<strong>Output:</strong> [0]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
<strong>Output:</strong> [8,9,9,9,0,0,0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in each linked list is in the range <code>[1, 100]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 9</code></li>
	<li>It is guaranteed that the list represents a number that does not have leading zeros.</li>
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
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode()
        carry, cur = 0, dummy
        while l1 or l2 or carry:
            s = (0 if not l1 else l1.val) + (0 if not l2 else l2.val) + carry
            carry, val = divmod(s, 10)
            cur.next = ListNode(val)
            cur = cur.next
            l1 = None if not l1 else l1.next
            l2 = None if not l2 else l2.next
        return dummy.next
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = s / 10;
            cur.next = new ListNode(s % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
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
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode();
        int carry = 0;
        ListNode* cur = dummy;
        while (l1 || l2 || carry) {
            int s = (l1 ? l1->val : 0) + (l2 ? l2->val : 0) + carry;
            carry = s / 10;
            cur->next = new ListNode(s % 10);
            cur = cur->next;
            l1 = l1 ? l1->next : nullptr;
            l2 = l2 ? l2->next : nullptr;
        }
        return dummy->next;
    }
};
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var addTwoNumbers = function (l1, l2) {
    const dummy = new ListNode();
    let carry = 0;
    let cur = dummy;
    while (l1 || l2 || carry) {
        const s = (l1?.val || 0) + (l2?.val || 0) + carry;
        carry = Math.floor(s / 10);
        cur.next = new ListNode(s % 10);
        cur = cur.next;
        l1 = l1?.next;
        l2 = l2?.next;
    }
    return dummy.next;
};
```

### **C#**

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode AddTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        int carry = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = s / 10;
            cur.next = new ListNode(s % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
    }
}
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
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    dummy := &ListNode{}
    carry := 0
    cur := dummy
    for l1 != nil || l2 != nil || carry != 0 {
        s := carry
        if l1 != nil {
            s += l1.Val
        }
        if l2 != nil {
            s += l2.Val
        }
        carry = s / 10
        cur.Next = &ListNode{s % 10, nil}
        cur = cur.Next
        if l1 != nil {
            l1 = l1.Next
        }
        if l2 != nil {
            l2 = l2.Next
        }
    }
    return dummy.Next
}
```

### **Ruby**

```rb
# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} l1
# @param {ListNode} l2
# @return {ListNode}
def add_two_numbers(l1, l2)
    dummy = ListNode.new()
    carry = 0
    cur = dummy
    while !l1.nil? || !l2.nil? || carry > 0
        s = (l1.nil? ? 0 : l1.val) + (l2.nil? ? 0 : l2.val) + carry
        carry = s / 10
        cur.next = ListNode.new(s % 10)
        cur = cur.next
        l1 = l1.nil? ? l1 : l1.next
        l2 = l2.nil? ? l2 : l2.next
    end
    dummy.next
end
```

### **Swift**

```swift
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public var val: Int
 *     public var next: ListNode?
 *     public init() { self.val = 0; self.next = nil; }
 *     public init(_ val: Int) { self.val = val; self.next = nil; }
 *     public init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next; }
 * }
 */
class Solution {
    func addTwoNumbers(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        var dummy = ListNode.init()
        var carry = 0
        var l1 = l1
        var l2 = l2
        var cur = dummy
        while l1 != nil || l2 != nil || carry != 0 {
            let s = (l1?.val ?? 0) + (l2?.val ?? 0) + carry
            carry = s / 10
            cur.next = ListNode.init(s % 10)
            cur = cur.next!
            l1 = l1?.next
            l2 = l2?.next
        }
        return dummy.next
    }
}
```

### **Nim**

```nim
#[
    # Driver code in the solution file
    # Definition for singly-linked list.
    type
    Node[int] = ref object
        value: int
        next: Node[int]

    SinglyLinkedList[T] = object
        head, tail: Node[T]
]#

# More efficient code churning ...
proc addTwoNumbers(l1: var SinglyLinkedList, l2: var SinglyLinkedList): SinglyLinkedList[int] =
  var
    aggregate: SinglyLinkedList
    psum: seq[char]
    temp_la, temp_lb: seq[int]

  while not l1.head.isNil:
    temp_la.add(l1.head.value)
    l1.head = l1.head.next

  while not l2.head.isNil:
    temp_lb.add(l2.head.value)
    l2.head = l2.head.next

  psum = reversed($(reversed(temp_la).join("").parseInt() + reversed(temp_lb).join("").parseInt()))
  for i in psum: aggregate.append(($i).parseInt())

  result = aggregate
```

### **...**

```

```

<!-- tabs:end -->

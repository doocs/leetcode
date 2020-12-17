# [707. Design Linked List](https://leetcode.com/problems/design-linked-list)

[中文文档](/solution/0700-0799/0707.Design%20Linked%20List/README.md)

## Description

<p>Design your&nbsp;implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly&nbsp;linked list should have two attributes: <code>val</code>&nbsp;and <code>next</code>. <code>val</code> is the value of the current node, and <code>next</code>&nbsp;is&nbsp;a&nbsp;pointer/reference to the next node. If you want to use the doubly linked list,&nbsp;you will need&nbsp;one more attribute <code>prev</code> to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.</p>

<p>Implement these functions in your linked list class:</p>

<ul>
	<li><code>get(index)</code> : Get the value of&nbsp;the <code>index</code>-th&nbsp;node in the linked list. If the index is invalid, return <code>-1</code>.</li>
	<li><code>addAtHead(val)</code> : Add a node of value <code>val</code>&nbsp;before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.</li>
	<li><code>addAtTail(val)</code> : Append a node of value <code>val</code>&nbsp;to the last element of the linked list.</li>
	<li><code>addAtIndex(index, val)</code> : Add a node of value <code>val</code>&nbsp;before the <code>index</code>-th&nbsp;node in the linked list.&nbsp;If <code>index</code>&nbsp;equals&nbsp;to the length of&nbsp;linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.</li>
	<li><code>deleteAtIndex(index)</code> : Delete&nbsp;the <code>index</code>-th&nbsp;node in the linked list, if the index is valid.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre>
<b>Input: </b>
[&quot;MyLinkedList&quot;,&quot;addAtHead&quot;,&quot;addAtTail&quot;,&quot;addAtIndex&quot;,&quot;get&quot;,&quot;deleteAtIndex&quot;,&quot;get&quot;]
[[],[1],[3],[1,2],[1],[1],[1]]
<b>Output: </b> 
[null,null,null,null,2,null,3]

<b>Explanation:</b>
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1-&gt;2-&gt;3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1-&gt;3
linkedList.get(1);&nbsp;&nbsp;&nbsp;         // returns 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= index,val &lt;= 1000</code></li>
	<li>Please do not use the built-in LinkedList library.</li>
	<li>At most <code>2000</code>&nbsp;calls will be made to&nbsp;<code>get</code>,&nbsp;<code>addAtHead</code>,&nbsp;<code>addAtTail</code>,&nbsp; <code>addAtIndex</code> and&nbsp;<code>deleteAtIndex</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class ListNode:

    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class MyLinkedList:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.dummy = ListNode()
        self.count = 0


    def get(self, index: int) -> int:
        """
        Get the value of the index-th node in the linked list. If the index is invalid, return -1.
        """
        if index < 0 or index >= self.count:
            return -1
        cur = self.dummy.next
        for _ in range(index):
            cur = cur.next
        return cur.val


    def addAtHead(self, val: int) -> None:
        """
        Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
        """
        self.addAtIndex(0, val)


    def addAtTail(self, val: int) -> None:
        """
        Append a node of value val to the last element of the linked list.
        """
        self.addAtIndex(self.count, val)


    def addAtIndex(self, index: int, val: int) -> None:
        """
        Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
        """
        if index > self.count:
            return
        pre = self.dummy
        for _ in range(index):
            pre = pre.next
        pre.next = ListNode(val, pre.next)
        self.count += 1


    def deleteAtIndex(self, index: int) -> None:
        """
        Delete the index-th node in the linked list, if the index is valid.
        """
        if index < 0 or index >= self.count:
            return
        pre = self.dummy
        for _ in range(index):
            pre = pre.next
        t = pre.next
        pre.next = t.next
        t.next = None
        self.count -= 1



# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)
```

### **Java**

```java
class MyLinkedList {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this(val, null);
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private ListNode dummy;
    private int count;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        dummy = new ListNode(0);
        count = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= count) {
            return -1;
        }
        ListNode cur = dummy.next;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(count, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > count) {
            return;
        }
        ListNode pre = dummy;
        while (index-- > 0) {
            pre = pre.next;
        }
        pre.next = new ListNode(val, pre.next);
        ++count;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= count) {
            return;
        }
        ListNode pre = dummy;
        while (index-- > 0) {
            pre = pre.next;
        }
        ListNode t = pre.next;
        pre.next = t.next;
        t.next = null;
        --count;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

### **...**

```

```

<!-- tabs:end -->

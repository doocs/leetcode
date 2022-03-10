# [707. Design Linked List](https://leetcode.com/problems/design-linked-list)

[中文文档](/solution/0700-0799/0707.Design%20Linked%20List/README.md)

## Description

<p>Design your implementation of the linked list. You can choose to use a singly or doubly linked list.<br />
A node in a singly linked list should have two attributes: <code>val</code> and <code>next</code>. <code>val</code> is the value of the current node, and <code>next</code> is a pointer/reference to the next node.<br />
If you want to use the doubly linked list, you will need one more attribute <code>prev</code> to indicate the previous node in the linked list. Assume all nodes in the linked list are <strong>0-indexed</strong>.</p>

<p>Implement the <code>MyLinkedList</code> class:</p>

<ul>
	<li><code>MyLinkedList()</code> Initializes the <code>MyLinkedList</code> object.</li>
	<li><code>int get(int index)</code> Get the value of the <code>index<sup>th</sup></code> node in the linked list. If the index is invalid, return <code>-1</code>.</li>
	<li><code>void addAtHead(int val)</code> Add a node of value <code>val</code> before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.</li>
	<li><code>void addAtTail(int val)</code> Append a node of value <code>val</code> as the last element of the linked list.</li>
	<li><code>void addAtIndex(int index, int val)</code> Add a node of value <code>val</code> before the <code>index<sup>th</sup></code> node in the linked list. If <code>index</code> equals the length of the linked list, the node will be appended to the end of the linked list. If <code>index</code> is greater than the length, the node <strong>will not be inserted</strong>.</li>
	<li><code>void deleteAtIndex(int index)</code> Delete the <code>index<sup>th</sup></code> node in the linked list, if the index is valid.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MyLinkedList&quot;, &quot;addAtHead&quot;, &quot;addAtTail&quot;, &quot;addAtIndex&quot;, &quot;get&quot;, &quot;deleteAtIndex&quot;, &quot;get&quot;]
[[], [1], [3], [1, 2], [1], [1], [1]]
<strong>Output</strong>
[null, null, null, null, 2, null, 3]

<strong>Explanation</strong>
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1-&gt;2-&gt;3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1-&gt;3
myLinkedList.get(1);              // return 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= index, val &lt;= 1000</code></li>
	<li>Please do not use the built-in LinkedList library.</li>
	<li>At most <code>2000</code> calls will be made to <code>get</code>, <code>addAtHead</code>, <code>addAtTail</code>, <code>addAtIndex</code> and <code>deleteAtIndex</code>.</li>
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

### **TypeScript**

```ts
class LinkNode {
    public val: number;
    public next: LinkNode;

    constructor(val: number, next: LinkNode = null) {
        this.val = val;
        this.next = next;
    }
}

class MyLinkedList {
    public head: LinkNode;

    constructor() {
        this.head = null;
    }

    get(index: number): number {
        if (this.head == null) {
            return -1;
        }
        let cur = this.head;
        let idxCur = 0;
        while (idxCur < index) {
            if (cur.next == null) {
                return -1;
            }
            cur = cur.next;
            idxCur++;
        }
        return cur.val;
    }

    addAtHead(val: number): void {
        this.head = new LinkNode(val, this.head);
    }

    addAtTail(val: number): void {
        const newNode = new LinkNode(val);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        let cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
    }

    addAtIndex(index: number, val: number): void {
        if (index <= 0) {
            return this.addAtHead(val);
        }
        const dummy = new LinkNode(0, this.head);
        let cur = dummy;
        let idxCur = 0;
        while (idxCur < index) {
            if (cur.next == null) {
                return;
            }
            cur = cur.next;
            idxCur++;
        }
        cur.next = new LinkNode(val, cur.next || null);
    }

    deleteAtIndex(index: number): void {
        if (index == 0) {
            this.head = (this.head || {}).next;
            return;
        }
        const dummy = new LinkNode(0, this.head);
        let cur = dummy;
        let idxCur = 0;
        while (idxCur < index) {
            if (cur.next == null) {
                return;
            }
            cur = cur.next;
            idxCur++;
        }
        cur.next = (cur.next || {}).next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = new MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(val)
 * obj.addAtTail(val)
 * obj.addAtIndex(index,val)
 * obj.deleteAtIndex(index)
 */
```

### **Rust**

```rust
struct Node {
    val: i32,
    next: Option<Box<Node>>,
}

#[derive(Default)]
struct MyLinkedList {
    head: Option<Box<Node>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyLinkedList {
    fn new() -> Self {
        Default::default()
    }

    fn get(&self, index: i32) -> i32 {
        let mut cur = match self.head {
            None => return -1,
            Some(ref n) => n,
        };
        let mut idx_cur = 0;
        while idx_cur < index {
            match cur.next {
                None => return -1,
                Some(ref next) => {
                    cur = next;
                    idx_cur += 1;
                }
            }
        }
        cur.val
    }

    fn add_at_head(&mut self, val: i32) {
        self.head = Some(Box::new(Node {
            val,
            next: self.head.take(),
        }));
    }

    fn add_at_tail(&mut self, val: i32) {
        let new_node = Some(Box::new(Node { val, next: None }));
        let mut cur = match self.head {
            Some(ref mut n) => n,
            None => {
                self.head = new_node;
                return;
            }
        };
        while let Some(ref mut next) = cur.next {
            cur = next;
        }
        cur.next = new_node;
    }

    fn add_at_index(&mut self, index: i32, val: i32) {
        let mut dummy = Box::new(Node {
            val: 0,
            next: self.head.take()
        });
        let mut idx = 0;
        let mut cur = &mut dummy;
        while idx < index {
            if let Some(ref mut next) = cur.next {
                cur = next;
            } else {
                return
            }
            idx += 1;
        }
        cur.next = Some(Box::new(Node {
            val,
            next: cur.next.take()
        }));
        self.head = dummy.next;
    }

    fn delete_at_index(&mut self, index: i32) {
        let mut dummy = Box::new(Node {
            val: 0,
            next: self.head.take(),
        });
        let mut idx = 0;
        let mut cur = &mut dummy;
        while idx < index {
            if let Some(ref mut next) = cur.next {
                cur = next;
            }
            idx += 1;
        }
        cur.next = cur.next.take().and_then(|n| n.next);
        self.head = dummy.next;
    }
}


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * let obj = MyLinkedList::new();
 * let ret_1: i32 = obj.get(index);
 * obj.add_at_head(val);
 * obj.add_at_tail(val);
 * obj.add_at_index(index, val);
 * obj.delete_at_index(index);
 */
```

### **...**

```

```

<!-- tabs:end -->

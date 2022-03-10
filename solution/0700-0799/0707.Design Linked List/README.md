# [707. 设计链表](https://leetcode-cn.com/problems/design-linked-list)

[English Version](/solution/0700-0799/0707.Design%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：<code>val</code>&nbsp;和&nbsp;<code>next</code>。<code>val</code>&nbsp;是当前节点的值，<code>next</code>&nbsp;是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性&nbsp;<code>prev</code>&nbsp;以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。</p>

<p>在链表类中实现这些功能：</p>

<ul>
	<li>get(index)：获取链表中第&nbsp;<code>index</code>&nbsp;个节点的值。如果索引无效，则返回<code>-1</code>。</li>
	<li>addAtHead(val)：在链表的第一个元素之前添加一个值为&nbsp;<code>val</code>&nbsp;的节点。插入后，新节点将成为链表的第一个节点。</li>
	<li>addAtTail(val)：将值为&nbsp;<code>val</code> 的节点追加到链表的最后一个元素。</li>
	<li>addAtIndex(index,val)：在链表中的第&nbsp;<code>index</code>&nbsp;个节点之前添加值为&nbsp;<code>val</code>&nbsp; 的节点。如果&nbsp;<code>index</code>&nbsp;等于链表的长度，则该节点将附加到链表的末尾。如果 <code>index</code> 大于链表长度，则不会插入节点。如果<code>index</code>小于0，则在头部插入节点。</li>
	<li>deleteAtIndex(index)：如果索引&nbsp;<code>index</code> 有效，则删除链表中的第&nbsp;<code>index</code> 个节点。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-&gt; 2-&gt; 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-&gt; 3
linkedList.get(1);            //返回3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有<code>val</code>值都在&nbsp;<code>[1, 1000]</code>&nbsp;之内。</li>
	<li>操作次数将在&nbsp;&nbsp;<code>[1, 1000]</code>&nbsp;之内。</li>
	<li>请不要使用内置的 LinkedList 库。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

定义虚拟头结点 dummy，count 记录当前链表结点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

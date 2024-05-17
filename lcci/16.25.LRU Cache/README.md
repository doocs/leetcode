---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.25.LRU%20Cache/README.md
---

<!-- problem:start -->

# [面试题 16.25. LRU 缓存](https://leetcode.cn/problems/lru-cache-lcci)

[English Version](/lcci/16.25.LRU%20Cache/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计和构建一个&ldquo;最近最少使用&rdquo;缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。</p>

<p>它应该支持以下操作： 获取数据 <code>get</code> 和 写入数据 <code>put</code> 。</p>

<p>获取数据 <code>get(key)</code> - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。<br>
写入数据 <code>put(key, value)</code> - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。</p>

<p><strong>示例:</strong></p>

<pre>LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 双向链表

我们可以用“哈希表”和“双向链表”实现一个 LRU 缓存。

-   哈希表：用于存储 key 和对应的节点位置。
-   双向链表：用于存储节点数据，按照访问时间排序。

当访问一个节点时，如果节点存在，我们将其从原来的位置删除，并重新插入到链表头部。这样就能保证链表尾部存储的就是最近最久未使用的节点，当节点数量大于缓存最大空间时就淘汰链表尾部的节点。

当插入一个节点时，如果节点存在，我们将其从原来的位置删除，并重新插入到链表头部。如果不存在，我们首先检查缓存是否已满，如果已满，则删除链表尾部的节点，将新的节点插入链表头部。

时间复杂度 $O(1)$，空间复杂度 $O(capacity)$。

<!-- tabs:start -->

#### Python3

```python
class Node:
    def __init__(self, key=0, val=0):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:
    def __init__(self, capacity: int):
        self.cache = {}
        self.head = Node()
        self.tail = Node()
        self.capacity = capacity
        self.size = 0
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self.move_to_head(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            node.val = value
            self.move_to_head(node)
        else:
            node = Node(key, value)
            self.cache[key] = node
            self.add_to_head(node)
            self.size += 1
            if self.size > self.capacity:
                node = self.remove_tail()
                self.cache.pop(node.key)
                self.size -= 1

    def move_to_head(self, node):
        self.remove_node(node)
        self.add_to_head(node)

    def remove_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def add_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def remove_tail(self):
        node = self.tail.prev
        self.remove_node(node)
        return node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
```

#### Java

```java
class Node {
    int key;
    int val;
    Node prev;
    Node next;

    Node() {
    }

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    private Map<Integer, Node> cache = new HashMap<>();
    private Node head = new Node();
    private Node tail = new Node();
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            Node node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);
            ++size;
            if (size > capacity) {
                node = removeTail();
                cache.remove(node.key);
                --size;
            }
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

#### C++

```cpp
struct Node {
    int k;
    int v;
    Node* prev;
    Node* next;

    Node()
        : k(0)
        , v(0)
        , prev(nullptr)
        , next(nullptr) {}
    Node(int key, int val)
        : k(key)
        , v(val)
        , prev(nullptr)
        , next(nullptr) {}
};

class LRUCache {
public:
    LRUCache(int capacity)
        : cap(capacity)
        , size(0) {
        head = new Node();
        tail = new Node();
        head->next = tail;
        tail->prev = head;
    }

    int get(int key) {
        if (!cache.count(key)) return -1;
        Node* node = cache[key];
        moveToHead(node);
        return node->v;
    }

    void put(int key, int value) {
        if (cache.count(key)) {
            Node* node = cache[key];
            node->v = value;
            moveToHead(node);
        } else {
            Node* node = new Node(key, value);
            cache[key] = node;
            addToHead(node);
            ++size;
            if (size > cap) {
                node = removeTail();
                cache.erase(node->k);
                --size;
            }
        }
    }

private:
    unordered_map<int, Node*> cache;
    Node* head;
    Node* tail;
    int cap;
    int size;

    void moveToHead(Node* node) {
        removeNode(node);
        addToHead(node);
    }

    void removeNode(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    void addToHead(Node* node) {
        node->next = head->next;
        node->prev = head;
        head->next = node;
        node->next->prev = node;
    }

    Node* removeTail() {
        Node* node = tail->prev;
        removeNode(node);
        return node;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
```

#### Go

```go
type node struct {
	key, val   int
	prev, next *node
}

type LRUCache struct {
	capacity   int
	cache      map[int]*node
	head, tail *node
}

func Constructor(capacity int) LRUCache {
	head := new(node)
	tail := new(node)
	head.next = tail
	tail.prev = head
	return LRUCache{
		capacity: capacity,
		cache:    make(map[int]*node, capacity),
		head:     head,
		tail:     tail,
	}
}

func (this *LRUCache) Get(key int) int {
	n, ok := this.cache[key]
	if !ok {
		return -1
	}
	this.moveToFront(n)
	return n.val
}

func (this *LRUCache) Put(key int, value int) {
	n, ok := this.cache[key]
	if ok {
		n.val = value
		this.moveToFront(n)
		return
	}
	if len(this.cache) == this.capacity {
		back := this.tail.prev
		this.remove(back)
		delete(this.cache, back.key)
	}
	n = &node{key: key, val: value}
	this.pushFront(n)
	this.cache[key] = n
}

func (this *LRUCache) moveToFront(n *node) {
	this.remove(n)
	this.pushFront(n)
}

func (this *LRUCache) remove(n *node) {
	n.prev.next = n.next
	n.next.prev = n.prev
	n.prev = nil
	n.next = nil
}

func (this *LRUCache) pushFront(n *node) {
	n.prev = this.head
	n.next = this.head.next
	this.head.next.prev = n
	this.head.next = n
}
```

#### TypeScript

```ts
class LRUCache {
    capacity: number;
    map: Map<number, number>;
    constructor(capacity: number) {
        this.capacity = capacity;
        this.map = new Map();
    }

    get(key: number): number {
        if (this.map.has(key)) {
            const val = this.map.get(key)!;
            this.map.delete(key);
            this.map.set(key, val);
            return val;
        }
        return -1;
    }

    put(key: number, value: number): void {
        this.map.delete(key);
        this.map.set(key, value);
        if (this.map.size > this.capacity) {
            this.map.delete(this.map.keys().next().value);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
```

#### Rust

```rust
use std::cell::RefCell;
use std::collections::HashMap;
use std::rc::Rc;

struct Node {
    key: i32,
    value: i32,
    prev: Option<Rc<RefCell<Node>>>,
    next: Option<Rc<RefCell<Node>>>,
}

impl Node {
    #[inline]
    fn new(key: i32, value: i32) -> Self {
        Self {
            key,
            value,
            prev: None,
            next: None,
        }
    }
}

struct LRUCache {
    capacity: usize,
    cache: HashMap<i32, Rc<RefCell<Node>>>,
    head: Option<Rc<RefCell<Node>>>,
    tail: Option<Rc<RefCell<Node>>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl LRUCache {
    fn new(capacity: i32) -> Self {
        Self {
            capacity: capacity as usize,
            cache: HashMap::new(),
            head: None,
            tail: None,
        }
    }

    fn get(&mut self, key: i32) -> i32 {
        match self.cache.get(&key) {
            Some(node) => {
                let node = Rc::clone(node);
                self.remove(&node);
                self.push_front(&node);
                let value = node.borrow().value;
                value
            }
            None => -1,
        }
    }

    fn put(&mut self, key: i32, value: i32) {
        match self.cache.get(&key) {
            Some(node) => {
                let node = Rc::clone(node);
                node.borrow_mut().value = value;
                self.remove(&node);
                self.push_front(&node);
            }
            None => {
                let node = Rc::new(RefCell::new(Node::new(key, value)));
                self.cache.insert(key, Rc::clone(&node));
                self.push_front(&node);
                if self.cache.len() > self.capacity {
                    let back_key = self.pop_back().unwrap().borrow().key;
                    self.cache.remove(&back_key);
                }
            }
        };
    }

    fn push_front(&mut self, node: &Rc<RefCell<Node>>) {
        match self.head.take() {
            Some(head) => {
                head.borrow_mut().prev = Some(Rc::clone(node));
                node.borrow_mut().prev = None;
                node.borrow_mut().next = Some(head);
                self.head = Some(Rc::clone(node));
            }
            None => {
                self.head = Some(Rc::clone(node));
                self.tail = Some(Rc::clone(node));
            }
        };
    }

    fn remove(&mut self, node: &Rc<RefCell<Node>>) {
        match (node.borrow().prev.as_ref(), node.borrow().next.as_ref()) {
            (None, None) => {
                self.head = None;
                self.tail = None;
            }
            (None, Some(next)) => {
                self.head = Some(Rc::clone(next));
                next.borrow_mut().prev = None;
            }
            (Some(prev), None) => {
                self.tail = Some(Rc::clone(prev));
                prev.borrow_mut().next = None;
            }
            (Some(prev), Some(next)) => {
                next.borrow_mut().prev = Some(Rc::clone(prev));
                prev.borrow_mut().next = Some(Rc::clone(next));
            }
        };
    }

    fn pop_back(&mut self) -> Option<Rc<RefCell<Node>>> {
        match self.tail.take() {
            Some(tail) => {
                self.remove(&tail);
                Some(tail)
            }
            None => None,
        }
    }
}/**
 * Your LRUCache object will be instantiated and called as such:
 * let obj = LRUCache::new(capacity);
 * let ret_1: i32 = obj.get(key);
 * obj.put(key, value);
 */
```

#### C#

```cs
public class LRUCache {
    class Node {
        public Node Prev;
        public Node Next;
        public int Key;
        public int Val;
    }

    private Node head = new Node();
    private Node tail = new Node();
    private Dictionary<int, Node> cache = new Dictionary<int, Node>();
    private readonly int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.Next = tail;
        tail.Prev = head;
    }

    public int Get(int key) {
        Node node;
        if (cache.TryGetValue(key, out node)) {
            moveToHead(node);
            return node.Val;
        }
        return -1;
    }

    public void Put(int key, int Val) {
        Node node;
        if (cache.TryGetValue(key, out node)) {
            moveToHead(node);
            node.Val = Val;
        } else {
            node = new Node() { Key = key, Val = Val };
            cache.Add(key, node);
            addToHead(node);
            if (++size > capacity) {
                node = removeTail();
                cache.Remove(node.Key);
                --size;
            }
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.Prev.Next = node.Next;
        node.Next.Prev = node.Prev;
    }

    private void addToHead(Node node) {
        node.Next = head.Next;
        node.Prev = head;
        head.Next = node;
        node.Next.Prev = node;
    }

    private Node removeTail() {
        Node node = tail.Prev;
        removeNode(node);
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,Val);
 */
```

#### Swift

```swift
class Node {
    var key: Int
    var val: Int
    var prev: Node?
    var next: Node?

    init(_ key: Int = 0, _ val: Int = 0) {
        self.key = key
        self.val = val
    }
}

class LRUCache {
    private var cache: [Int: Node] = [:]
    private let head: Node = Node()
    private let tail: Node = Node()
    private var capacity: Int
    private var size: Int = 0

    init(_ capacity: Int) {
        self.capacity = capacity
        head.next = tail
        tail.prev = head
    }

    func get(_ key: Int) -> Int {
        guard let node = cache[key] else {
            return -1
        }
        moveToHead(node)
        return node.val
    }

    func put(_ key: Int, _ value: Int) {
        if let node = cache[key] {
            node.val = value
            moveToHead(node)
        } else {
            let newNode = Node(key, value)
            cache[key] = newNode
            addToHead(newNode)
            size += 1
            if size > capacity {
                if let tailNode = removeTail() {
                    cache.removeValue(forKey: tailNode.key)
                    size -= 1
                }
            }
        }
    }

    private func moveToHead(_ node: Node) {
        removeNode(node)
        addToHead(node)
    }

    private func removeNode(_ node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    private func addToHead(_ node: Node) {
        node.prev = head
        node.next = head.next
        head.next?.prev = node
        head.next = node
    }

    private func removeTail() -> Node? {
        guard let res = tail.prev, res !== head else {
            return nil
        }
        removeNode(res)
        return res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

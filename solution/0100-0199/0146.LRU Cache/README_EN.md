# [146. LRU Cache](https://leetcode.com/problems/lru-cache)

[中文文档](/solution/0100-0199/0146.LRU%20Cache/README.md)

## Description

<p>Design a data structure that follows the constraints of a <strong><a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU" target="_blank">Least Recently Used (LRU) cache</a></strong>.</p>

<p>Implement the <code>LRUCache</code> class:</p>

<ul>
	<li><code>LRUCache(int capacity)</code> Initialize the LRU cache with <strong>positive</strong> size <code>capacity</code>.</li>
	<li><code>int get(int key)</code> Return the value of the <code>key</code> if the key exists, otherwise return <code>-1</code>.</li>
	<li><code>void put(int key, int value)</code> Update the value of the <code>key</code> if the <code>key</code> exists. Otherwise, add the <code>key-value</code> pair to the cache. If the number of keys exceeds the <code>capacity</code> from this operation, <strong>evict</strong> the least recently used key.</li>
</ul>

<p>The functions <code>get</code> and <code>put</code> must each run in <code>O(1)</code> average time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LRUCache&quot;, &quot;put&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;get&quot;, &quot;get&quot;]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
<strong>Output</strong>
[null, null, null, 1, null, -1, null, -1, 3, 4]

<strong>Explanation</strong>
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity &lt;= 3000</code></li>
	<li><code>0 &lt;= key &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls will be made to <code>get</code> and <code>put</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **Rust**

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
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * let obj = LRUCache::new(capacity);
 * let ret_1: i32 = obj.get(key);
 * obj.put(key, value);
 */
```

### **Go**

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

### **C++**

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
        , next(nullptr) { }
    Node(int key, int val)
        : k(key)
        , v(val)
        , prev(nullptr)
        , next(nullptr) { }
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

### **...**

```

```

<!-- tabs:end -->

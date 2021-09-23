# [146. LRU Cache](https://leetcode.com/problems/lru-cache)

[中文文档](/solution/0100-0199/0146.LRU%20Cache/README.md)

## Description

<p>Design a data structure that follows the constraints of a <strong><a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU" target="_blank">Least Recently Used (LRU) cache</a></strong>.</p>

<p>Implement the <code>LRUCache</code> class:</p>

<ul>
	<li><code>LRUCache(int capacity)</code> Initialize the LRU cache with <strong>positive</strong> size <code>capacity</code>.</li>
	<li><code>int get(int key)</code> Return the value of the <code>key</code> if the key exists, otherwise return <code>-1</code>.</li>
	<li><code>void put(int key, int value)</code>&nbsp;Update the value of the <code>key</code> if the <code>key</code> exists. Otherwise, add the <code>key-value</code> pair to the cache. If the number of keys exceeds the <code>capacity</code> from this operation, <strong>evict</strong> the least recently used key.</li>
</ul>

<p><b>Follow up:</b><br />
Could you do <code>get</code> and <code>put</code> in <code>O(1)</code> time complexity?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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
	<li><code>0 &lt;= key &lt;= 3000</code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>4</sup></code></li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>get</code> and <code>put</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Node:
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
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
        return node.value

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            node.value = value
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
        self.head.next.prev = node
        self.head.next = node
        node.prev = self.head

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
class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node() {

        }
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
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
        head.next.prev = node;
        head.next = node;
        node.prev = head;
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
use std::collections::hash_map::HashMap;
use std::rc::Rc;

struct Node {
    key: i32,
    value: i32,
    prev: Option<Rc<RefCell<Node>>>,
    next: Option<Rc<RefCell<Node>>>,
}

impl Node {
    #[inline]
    fn new(key: i32, value: i32) -> Node {
        Node {
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

impl LRUCache {
    fn new(capacity: i32) -> Self {
        LRUCache {
            capacity: capacity as usize,
            cache: HashMap::new(),
            head: None,
            tail: None,
        }
    }

    fn get(&mut self, key: i32) -> i32 {
        if let Some(node) = self.cache.get(&key) {
            let node = Rc::clone(node);
            self.remove(&node);
            self.push_front(&node);
            let value = node.borrow().value;
            value
        } else {
            -1
        }
    }

    fn put(&mut self, key: i32, value: i32) {
        if let Some(node) = self.cache.get(&key) {
            let node = Rc::clone(node);
            node.borrow_mut().value = value;
            self.remove(&node);
            self.push_front(&node);
        } else {
            let node = Rc::new(RefCell::new(Node::new(key, value)));
            self.cache.insert(key, Rc::clone(&node));
            self.push_front(&node);
            if self.cache.len() > self.capacity {
                if let Some(back) = self.pop_back() {
                    self.cache.remove(&back.borrow().key);
                }
            }
        }
    }

    fn push_front(&mut self, node: &Rc<RefCell<Node>>) {
        let mut node_borrow_mut = node.borrow_mut();
        if let Some(head) = self.head.take() {
            head.borrow_mut().prev = Some(Rc::clone(node));
            node_borrow_mut.next = Some(head);
            node_borrow_mut.prev = None;
            self.head = Some(Rc::clone(node));
        } else {
            self.head = Some(Rc::clone(node));
            self.tail = Some(Rc::clone(node));
        }
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
        }
    }

    fn pop_back(&mut self) -> Option<Rc<RefCell<Node>>> {
        if let Some(tail) = self.tail.take() {
            match tail.borrow().prev.as_ref() {
                Some(prev) => {
                    prev.borrow_mut().next = None;
                    self.tail = Some(Rc::clone(prev));
                }
                None => {
                    self.head = None;
                    self.tail = None;
                }
            }
            Some(tail)
        } else {
            None
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->

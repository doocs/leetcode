# [460. LFU 缓存](https://leetcode.cn/problems/lfu-cache)

[English Version](/solution/0400-0499/0460.LFU%20Cache/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你为 <a href="https://baike.baidu.com/item/%E7%BC%93%E5%AD%98%E7%AE%97%E6%B3%95">最不经常使用（LFU）</a>缓存算法设计并实现数据结构。</p>

<p>实现 <code>LFUCache</code> 类：</p>

<ul>
	<li><code>LFUCache(int capacity)</code> - 用数据结构的容量&nbsp;<code>capacity</code> 初始化对象</li>
	<li><code>int get(int key)</code>&nbsp;- 如果键&nbsp;<code>key</code> 存在于缓存中，则获取键的值，否则返回 <code>-1</code> 。</li>
	<li><code>void put(int key, int value)</code>&nbsp;- 如果键&nbsp;<code>key</code> 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量&nbsp;<code>capacity</code> 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 <strong>最近最久未使用</strong> 的键。</li>
</ul>

<p>为了确定最不常使用的键，可以为缓存中的每个键维护一个 <strong>使用计数器</strong> 。使用计数最小的键是最久未使用的键。</p>

<p>当一个键首次插入到缓存中时，它的使用计数器被设置为 <code>1</code> (由于 put 操作)。对缓存中的键执行 <code>get</code> 或 <code>put</code> 操作，使用计数器的值将会递增。</p>

<p>函数 <code>get</code> 和 <code>put</code> 必须以 <code>O(1)</code> 的平均时间复杂度运行。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
<strong>输出：</strong>
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

<strong>解释：</strong>
// cnt(x) = 键 x 的使用计数
// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // 返回 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // 返回 -1（未找到）
lfu.get(3);      // 返回 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // 返回 -1（未找到）
lfu.get(3);      // 返回 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // 返回 4
                 // cache=[3,4], cnt(4)=2, cnt(3)=3</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= capacity&nbsp;&lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= key &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li>最多调用 <code>2 * 10<sup>5</sup></code> 次 <code>get</code> 和 <code>put</code> 方法</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

和 [LRU 缓存](/solution/0100-0199/0146.Lru%20Cache/README.md) 类似的思路，用 `map<key, node>` 和 `map<freq, list<node>>` 存储不同使用频率的节点

对于 `get` 操作，判断 key 是否存在哈希表中：

-   若不存在，返回 -1
-   若存在，增加节点的使用频率，返回节点值

对于 `put` 操作，同样判断 key 是否存在哈希表中：

-   若不存在，首先判断缓存容量是否足够，不够的话需要先删除使用次数最少的节点。然后再创建新节点，插入使用频率为 1 的双链表
-   若存在，修改原节点的值，增加节点的使用频率

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class LFUCache {


    private final Map<Integer, Node> map;
    private final Map<Integer, DoublyLinkedList> freqMap;
    private final int capacity;
    private int minFreq;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity, 1);
        freqMap = new HashMap<>();
    }


    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        incrFreq(node);
        return node.value;
    }


    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            incrFreq(node);
            return;
        }
        if (map.size() == capacity) {
            DoublyLinkedList list = freqMap.get(minFreq);
            map.remove(list.removeLast().key);
        }
        Node node = new Node(key, value);
        addNode(node);
        map.put(key, node);
        minFreq = 1;
    }


    private void incrFreq(Node node) {
        int freq = node.freq;
        DoublyLinkedList list = freqMap.get(freq);
        list.remove(node);
        if (list.isEmpty()) {
            freqMap.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }
        node.freq++;
        addNode(node);
    }


    private void addNode(Node node) {
        int freq = node.freq;
        DoublyLinkedList list = freqMap.getOrDefault(freq, new DoublyLinkedList());
        list.addFirst(node);
        freqMap.put(freq, list);
    }


    private static class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;


        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }


    private static class DoublyLinkedList {


        private final Node head;
        private final Node tail;


        public DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }


        public void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }


        public Node remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.next = null;
            node.prev = null;
            return node;
        }


        public Node removeLast() {
            return remove(tail.prev);
        }


        public boolean isEmpty() {
            return head.next == tail;
        }
    }
}
```

### **Go**

```go
type LFUCache struct {
	cache    map[int]*node
	freqMap  map[int]*list
	minFreq  int
	capacity int
}


func Constructor(capacity int) LFUCache {
	return LFUCache{
		cache:    make(map[int]*node),
		freqMap:  make(map[int]*list),
		capacity: capacity,
	}
}


func (this *LFUCache) Get(key int) int {
	if this.capacity == 0 {
		return -1
	}


	n, ok := this.cache[key]
	if !ok {
		return -1
	}


	this.incrFreq(n)
	return n.val
}


func (this *LFUCache) Put(key int, value int) {
	if this.capacity == 0 {
		return
	}


	n, ok := this.cache[key]
	if ok {
		n.val = value
		this.incrFreq(n)
		return
	}


	if len(this.cache) == this.capacity {
		l := this.freqMap[this.minFreq]
		delete(this.cache, l.removeBack().key)
	}
	n = &node{key: key, val: value, freq: 1}
	this.addNode(n)
	this.cache[key] = n
	this.minFreq = 1
}


func (this *LFUCache) incrFreq(n *node) {
	l := this.freqMap[n.freq]
	l.remove(n)
	if l.empty() {
		delete(this.freqMap, n.freq)
		if n.freq == this.minFreq {
			this.minFreq++
		}
	}
	n.freq++
	this.addNode(n)
}


func (this *LFUCache) addNode(n *node) {
	l, ok := this.freqMap[n.freq]
	if !ok {
		l = newList()
		this.freqMap[n.freq] = l
	}
	l.pushFront(n)
}


type node struct {
	key  int
	val  int
	freq int
	prev *node
	next *node
}


type list struct {
	head *node
	tail *node
}


func newList() *list {
	head := new(node)
	tail := new(node)
	head.next = tail
	tail.prev = head
	return &list{
		head: head,
		tail: tail,
	}
}


func (l *list) pushFront(n *node) {
	n.prev = l.head
	n.next = l.head.next
	l.head.next.prev = n
	l.head.next = n
}


func (l *list) remove(n *node) {
	n.prev.next = n.next
	n.next.prev = n.prev
	n.next = nil
	n.prev = nil
}


func (l *list) removeBack() *node {
	n := l.tail.prev
	l.remove(n)
	return n
}


func (l *list) empty() bool {
	return l.head.next == l.tail
}
```

### **Rust**

```rust
use std::cell::RefCell;
use std::collections::HashMap;
use std::rc::Rc;


struct Node {
    key: i32,
    value: i32,
    freq: i32,
    prev: Option<Rc<RefCell<Node>>>,
    next: Option<Rc<RefCell<Node>>>,
}


impl Node {
    fn new(key: i32, value: i32) -> Self {
        Self {
            key,
            value,
            freq: 1,
            prev: None,
            next: None,
        }
    }
}


struct LinkedList {
    head: Option<Rc<RefCell<Node>>>,
    tail: Option<Rc<RefCell<Node>>>,
}


impl LinkedList {
    fn new() -> Self {
        Self {
            head: None,
            tail: None,
        }
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
                node.borrow_mut().prev = None;
                node.borrow_mut().next = None;
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


    fn is_empty(&self) -> bool {
        self.head.is_none()
    }
}


struct LFUCache {
    cache: HashMap<i32, Rc<RefCell<Node>>>,
    freq_map: HashMap<i32, LinkedList>,
    min_freq: i32,
    capacity: usize,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl LFUCache {
    fn new(capacity: i32) -> Self {
        Self {
            cache: HashMap::new(),
            freq_map: HashMap::new(),
            min_freq: 0,
            capacity: capacity as usize,
        }
    }


    fn get(&mut self, key: i32) -> i32 {
        if self.capacity == 0 {
            return -1;
        }


        match self.cache.get(&key) {
            Some(node) => {
                let node = Rc::clone(node);
                self.incr_freq(&node);
                let value = node.borrow().value;
                value
            }
            None => -1,
        }
    }


    fn put(&mut self, key: i32, value: i32) {
        if self.capacity == 0 {
            return;
        }


        match self.cache.get(&key) {
            Some(node) => {
                let node = Rc::clone(node);
                node.borrow_mut().value = value;
                self.incr_freq(&node);
            }
            None => {
                if self.cache.len() == self.capacity {
                    let list = self.freq_map.get_mut(&self.min_freq).unwrap();
                    self.cache.remove(&list.pop_back().unwrap().borrow().key);
                }
                let node = Rc::new(RefCell::new(Node::new(key, value)));
                self.add_node(&node);
                self.cache.insert(key, node);
                self.min_freq = 1;
            }
        };
    }


    fn incr_freq(&mut self, node: &Rc<RefCell<Node>>) {
        let freq = node.borrow().freq;
        let list = self.freq_map.get_mut(&freq).unwrap();
        list.remove(node);
        if list.is_empty() {
            self.freq_map.remove(&freq);
            if freq == self.min_freq {
                self.min_freq += 1;
            }
        }
        node.borrow_mut().freq += 1;
        self.add_node(node);
    }


    fn add_node(&mut self, node: &Rc<RefCell<Node>>) {
        let freq = node.borrow().freq;
        match self.freq_map.get_mut(&freq) {
            Some(list) => {
                list.push_front(node);
            }
            None => {
                let mut list = LinkedList::new();
                list.push_front(node);
                self.freq_map.insert(node.borrow().freq, list);
            }
        };
    }
}


/**
 * Your LFUCache object will be instantiated and called as such:
 * let obj = LFUCache::new(capacity);
 * let ret_1: i32 = obj.get(key);
 * obj.put(key, value);
 */
```

### **...**

```

```

<!-- tabs:end -->

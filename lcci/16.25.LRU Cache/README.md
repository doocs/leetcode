# [面试题 16.25. LRU 缓存](https://leetcode.cn/problems/lru-cache-lcci)

[English Version](/lcci/16.25.LRU%20Cache/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表 + 双向链表”实现。其中：

-   双向链表按照被使用的顺序存储 kv 键值对，靠近头部的 kv 键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
-   哈希表通过缓存的 key 映射到双向链表中的位置。我们可以在 `O(1)` 时间内定位到缓存的 key 所对应的 value 在链表中的位置。

对于 `get` 操作，判断 key 是否存在哈希表中：

-   若不存在，返回 -1
-   若存在，则 key 对应的节点 node 是最近使用的节点。将该节点移动到双向链表的头部，最后返回该节点的值即可。

对于 `put` 操作，同样先判断 key 是否存在哈希表中：

-   若不存在，则创建一个新的 node 节点，放入哈希表中。然后在双向链表的头部添加该节点。接着判断双向链表节点数是否超过 capacity。若超过，则删除双向链表的尾部节点，以及在哈希表中对应的项。
-   若存在，则更新 node 节点的值，然后该节点移动到双向链表的头部。

双向链表节点（哈希表的 value）的结构如下：

```java
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
```

你可能会问，哈希表的 value 为何还要存放 key？

这是因为，双向链表有一个删除尾节点的操作。我们定位到双向链表的尾节点，在链表中删除之后，还要找到该尾节点在哈希表中的位置，因此需要根据 value 中存放的 key，定位到哈希表的数据项，然后将其删除。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **...**

```

```

<!-- tabs:end -->

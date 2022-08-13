# [432. 全 O(1) 的数据结构](https://leetcode.cn/problems/all-oone-data-structure)

[English Version](/solution/0400-0499/0432.All%20O%60one%20Data%20Structure/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。</p>

<p>实现 <code>AllOne</code> 类：</p>

<ul>
	<li><code>AllOne()</code> 初始化数据结构的对象。</li>
	<li><code>inc(String key)</code> 字符串 <code>key</code> 的计数增加 <code>1</code> 。如果数据结构中尚不存在 <code>key</code> ，那么插入计数为 <code>1</code> 的 <code>key</code> 。</li>
	<li><code>dec(String key)</code> 字符串 <code>key</code> 的计数减少 <code>1</code> 。如果 <code>key</code> 的计数在减少后为 <code>0</code> ，那么需要将这个 <code>key</code> 从数据结构中删除。测试用例保证：在减少计数前，<code>key</code> 存在于数据结构中。</li>
	<li><code>getMaxKey()</code> 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 <code>""</code> 。</li>
	<li><code>getMinKey()</code> 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 <code>""</code> 。</li>
</ul>

<p><strong>注意：</strong>每个函数都应当满足 <code>O(1)</code> 平均时间复杂度。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
<strong>输出</strong>
[null, null, null, "hello", "hello", null, "hello", "leet"]

<strong>解释</strong>
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // 返回 "hello"
allOne.getMinKey(); // 返回 "hello"
allOne.inc("leet");
allOne.getMaxKey(); // 返回 "hello"
allOne.getMinKey(); // 返回 "leet"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= key.length &lt;= 10</code></li>
	<li><code>key</code> 由小写英文字母组成</li>
	<li>测试用例保证：在每次调用 <code>dec</code> 时，数据结构中总存在 <code>key</code></li>
	<li>最多调用 <code>inc</code>、<code>dec</code>、<code>getMaxKey</code> 和 <code>getMinKey</code> 方法 <code>5 * 10<sup>4</sup></code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Node:
    def __init__(self, key='', cnt=0):
        self.prev = None
        self.next = None
        self.cnt = cnt
        self.keys = {key}

    def insert(self, node):
        node.prev = self
        node.next = self.next
        node.prev.next = node
        node.next.prev = node
        return node

    def remove(self):
        self.prev.next = self.next
        self.next.prev = self.prev


class AllOne:
    def __init__(self):
        self.root = Node()
        self.root.next = self.root
        self.root.prev = self.root
        self.nodes = {}

    def inc(self, key: str) -> None:
        root, nodes = self.root, self.nodes
        if key not in nodes:
            if root.next == root or root.next.cnt > 1:
                nodes[key] = root.insert(Node(key, 1))
            else:
                root.next.keys.add(key)
                nodes[key] = root.next
        else:
            curr = nodes[key]
            next = curr.next
            if next == root or next.cnt > curr.cnt + 1:
                nodes[key] = curr.insert(Node(key, curr.cnt + 1))
            else:
                next.keys.add(key)
                nodes[key] = next
            curr.keys.discard(key)
            if not curr.keys:
                curr.remove()

    def dec(self, key: str) -> None:
        root, nodes = self.root, self.nodes
        curr = nodes[key]
        if curr.cnt == 1:
            nodes.pop(key)
        else:
            prev = curr.prev
            if prev == root or prev.cnt < curr.cnt - 1:
                nodes[key] = prev.insert(Node(key, curr.cnt - 1))
            else:
                prev.keys.add(key)
                nodes[key] = prev
        curr.keys.discard(key)
        if not curr.keys:
            curr.remove()

    def getMaxKey(self) -> str:
        return next(iter(self.root.prev.keys))

    def getMinKey(self) -> str:
        return next(iter(self.root.next.keys))


# Your AllOne object will be instantiated and called as such:
# obj = AllOne()
# obj.inc(key)
# obj.dec(key)
# param_3 = obj.getMaxKey()
# param_4 = obj.getMinKey()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class AllOne {
    Node root = new Node();
    Map<String, Node> nodes = new HashMap<>();

    public AllOne() {
        root.next = root;
        root.prev = root;
    }

    public void inc(String key) {
        if (!nodes.containsKey(key)) {
            if (root.next == root || root.next.cnt > 1) {
                nodes.put(key, root.insert(new Node(key, 1)));
            } else {
                root.next.keys.add(key);
                nodes.put(key, root.next);
            }
        } else {
            Node curr = nodes.get(key);
            Node next = curr.next;
            if (next == root || next.cnt > curr.cnt + 1) {
                nodes.put(key, curr.insert(new Node(key, curr.cnt + 1)));
            } else {
                next.keys.add(key);
                nodes.put(key, next);
            }
            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                curr.remove();
            }
        }
    }

    public void dec(String key) {
        Node curr = nodes.get(key);
        if (curr.cnt == 1) {
            nodes.remove(key);
        } else {
            Node prev = curr.prev;
            if (prev == root || prev.cnt < curr.cnt - 1) {
                nodes.put(key, prev.insert(new Node(key, curr.cnt - 1)));
            } else {
                prev.keys.add(key);
                nodes.put(key, prev);
            }
        }

        curr.keys.remove(key);
        if (curr.keys.isEmpty()) {
            curr.remove();
        }
    }

    public String getMaxKey() {
        return root.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return root.next.keys.iterator().next();
    }
}

class Node {
    Node prev;
    Node next;
    int cnt;
    Set<String> keys = new HashSet<>();

    public Node() {
        this("", 0);
    }

    public Node(String key, int cnt) {
        this.cnt = cnt;
        keys.add(key);
    }

    public Node insert(Node node) {
        node.prev = this;
        node.next = this.next;
        node.prev.next = node;
        node.next.prev = node;
        return node;
    }

    public void remove() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
```

### **...**

```

```

<!-- tabs:end -->

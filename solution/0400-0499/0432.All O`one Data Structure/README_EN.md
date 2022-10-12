# [432. All O`one Data Structure](https://leetcode.com/problems/all-oone-data-structure)

[中文文档](/solution/0400-0499/0432.All%20O%60one%20Data%20Structure/README.md)

## Description

<p>Design a data structure to store the strings&#39; count with the ability to return the strings with minimum and maximum counts.</p>

<p>Implement the <code>AllOne</code> class:</p>

<ul>
	<li><code>AllOne()</code> Initializes the object of the data structure.</li>
	<li><code>inc(String key)</code> Increments the count of the string <code>key</code> by <code>1</code>. If <code>key</code> does not exist in the data structure, insert it with count <code>1</code>.</li>
	<li><code>dec(String key)</code> Decrements the count of the string <code>key</code> by <code>1</code>. If the count of <code>key</code> is <code>0</code> after the decrement, remove it from the data structure. It is guaranteed that <code>key</code> exists in the data structure before the decrement.</li>
	<li><code>getMaxKey()</code> Returns one of the keys with the maximal count. If no element exists, return an empty string <code>&quot;&quot;</code>.</li>
	<li><code>getMinKey()</code> Returns one of the keys with the minimum count. If no element exists, return an empty string <code>&quot;&quot;</code>.</li>
</ul>

<p><strong>Note</strong> that each function must run in <code>O(1)</code> average time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;AllOne&quot;, &quot;inc&quot;, &quot;inc&quot;, &quot;getMaxKey&quot;, &quot;getMinKey&quot;, &quot;inc&quot;, &quot;getMaxKey&quot;, &quot;getMinKey&quot;]
[[], [&quot;hello&quot;], [&quot;hello&quot;], [], [], [&quot;leet&quot;], [], []]
<strong>Output</strong>
[null, null, null, &quot;hello&quot;, &quot;hello&quot;, null, &quot;hello&quot;, &quot;leet&quot;]

<strong>Explanation</strong>
AllOne allOne = new AllOne();
allOne.inc(&quot;hello&quot;);
allOne.inc(&quot;hello&quot;);
allOne.getMaxKey(); // return &quot;hello&quot;
allOne.getMinKey(); // return &quot;hello&quot;
allOne.inc(&quot;leet&quot;);
allOne.getMaxKey(); // return &quot;hello&quot;
allOne.getMinKey(); // return &quot;leet&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= key.length &lt;= 10</code></li>
	<li><code>key</code> consists of lowercase English letters.</li>
	<li>It is guaranteed that for each call to <code>dec</code>, <code>key</code> is existing in the data structure.</li>
	<li>At most <code>5 * 10<sup>4</sup></code>&nbsp;calls will be made to <code>inc</code>, <code>dec</code>, <code>getMaxKey</code>, and <code>getMinKey</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

# [716. 最大栈 🔒](https://leetcode.cn/problems/max-stack)

[English Version](/solution/0700-0799/0716.Max%20Stack/README_EN.md)

<!-- tags:栈,设计,链表,双向链表,有序集合 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。</p>

<p>实现 <code>MaxStack</code> 类：</p>

<ul>
	<li><code>MaxStack()</code> 初始化栈对象</li>
	<li><code>void push(int x)</code> 将元素 x 压入栈中。</li>
	<li><code>int pop()</code> 移除栈顶元素并返回这个元素。</li>
	<li><code>int top()</code> 返回栈顶元素，无需移除。</li>
	<li><code>int peekMax()</code> 检索并返回栈中最大元素，无需移除。</li>
	<li><code>int popMax()</code> 检索并返回栈中最大元素，并将其移除。如果有多个最大元素，只要移除 <strong>最靠近栈顶</strong> 的那个。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
<strong>输出</strong>
[null, null, null, null, 5, 5, 1, 5, 1, 5]

<strong>解释</strong>
MaxStack stk = new MaxStack();
stk.push(5);   // [<strong>5</strong>] - 5 既是栈顶元素，也是最大元素
stk.push(1);   // [<strong>5</strong>, <strong>1</strong>] - 栈顶元素是 1，最大元素是 5
stk.push(5);   // [5, 1, <strong>5</strong>] - 5 既是栈顶元素，也是最大元素
stk.top();     // 返回 5，[5, 1, <strong>5</strong>] - 栈没有改变
stk.popMax();  // 返回 5，[<strong>5</strong>, <strong>1</strong>] - 栈发生改变，栈顶元素不再是最大元素
stk.top();     // 返回 1，[<strong>5</strong>, <strong>1</strong>] - 栈没有改变
stk.peekMax(); // 返回 5，[<strong>5</strong>, <strong>1</strong>] - 栈没有改变
stk.pop();     // 返回 1，[<strong>5</strong>] - 此操作后，5 既是栈顶元素，也是最大元素
stk.top();     // 返回 5，[<strong>5</strong>] - 栈没有改变
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>7</sup> <= x <= 10<sup>7</sup></code></li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>push</code>、<code>pop</code>、<code>top</code>、<code>peekMax</code> 和 <code>popMax</code></li>
	<li>调用 <code>pop</code>、<code>top</code>、<code>peekMax</code> 或 <code>popMax</code> 时，栈中 <strong>至少存在一个元素</strong></li>
</ul>

<p> </p>

<p><b>进阶：</b> </p>

<ul>
	<li>试着设计解决方案：调用 <code>top</code> 方法的时间复杂度为 <code>O(1)</code> ，调用其他方法的时间复杂度为 <code>O(logn)</code> 。 </li>
</ul>

## 解法

### 方法一：双向链表 + 有序集合

使用双向链表存储栈中的元素，使用有序集合存储栈中的元素，有序集合中的元素按照从小到大的顺序存储，每个元素都对应着双向链表中的一个节点。

-   调用 `push(x)` 方法时，将元素 `x` 插入到双向链表的末尾，同时将元素 `x` 对应的节点插入到有序集合中。时间复杂度 $O(\log n)$。
-   调用 `pop()` 方法时，将双向链表的末尾节点删除，同时将有序集合中的对应节点删除。时间复杂度 $O(\log n)$。
-   调用 `top()` 方法时，返回双向链表的末尾节点的值。时间复杂度 $O(1)$。
-   调用 `peekMax()` 方法时，返回有序集合中的最后一个元素对应的节点的值。时间复杂度 $O(\log n)$。
-   调用 `popMax()` 方法时，将有序集合中的最后一个元素删除，同时将对应的节点从双向链表中删除。时间复杂度 $O(\log n)$。

空间复杂度 $O(n)$。其中 $n$ 为栈中的元素个数。

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Node:
    def __init__(self, val=0):
        self.val = val
        self.prev: Union[Node, None] = None
        self.next: Union[Node, None] = None


class DoubleLinkedList:
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

    def append(self, val) -> Node:
        node = Node(val)
        node.next = self.tail
        node.prev = self.tail.prev
        self.tail.prev = node
        node.prev.next = node
        return node

    @staticmethod
    def remove(node) -> Node:
        node.prev.next = node.next
        node.next.prev = node.prev
        return node

    def pop(self) -> Node:
        return self.remove(self.tail.prev)

    def peek(self):
        return self.tail.prev.val


class MaxStack:
    def __init__(self):
        self.stk = DoubleLinkedList()
        self.sl = SortedList(key=lambda x: x.val)

    def push(self, x: int) -> None:
        node = self.stk.append(x)
        self.sl.add(node)

    def pop(self) -> int:
        node = self.stk.pop()
        self.sl.remove(node)
        return node.val

    def top(self) -> int:
        return self.stk.peek()

    def peekMax(self) -> int:
        return self.sl[-1].val

    def popMax(self) -> int:
        node = self.sl.pop()
        DoubleLinkedList.remove(node)
        return node.val


# Your MaxStack object will be instantiated and called as such:
# obj = MaxStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.peekMax()
# param_5 = obj.popMax()
```

```java
class Node {
    public int val;
    public Node prev, next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }
}

class DoubleLinkedList {
    private final Node head = new Node();
    private final Node tail = new Node();

    public DoubleLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    public Node append(int val) {
        Node node = new Node(val);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
        node.prev.next = node;
        return node;
    }

    public static Node remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    public Node pop() {
        return remove(tail.prev);
    }

    public int peek() {
        return tail.prev.val;
    }
}

class MaxStack {
    private DoubleLinkedList stk = new DoubleLinkedList();
    private TreeMap<Integer, List<Node>> tm = new TreeMap<>();

    public MaxStack() {
    }

    public void push(int x) {
        Node node = stk.append(x);
        tm.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }

    public int pop() {
        Node node = stk.pop();
        List<Node> nodes = tm.get(node.val);
        int x = nodes.remove(nodes.size() - 1).val;
        if (nodes.isEmpty()) {
            tm.remove(node.val);
        }
        return x;
    }

    public int top() {
        return stk.peek();
    }

    public int peekMax() {
        return tm.lastKey();
    }

    public int popMax() {
        int x = peekMax();
        List<Node> nodes = tm.get(x);
        Node node = nodes.remove(nodes.size() - 1);
        if (nodes.isEmpty()) {
            tm.remove(x);
        }
        DoubleLinkedList.remove(node);
        return x;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
```

```cpp
class MaxStack {
public:
    MaxStack() {
    }

    void push(int x) {
        stk.push_back(x);
        tm.insert({x, --stk.end()});
    }

    int pop() {
        auto it = --stk.end();
        int ans = *it;
        auto mit = --tm.upper_bound(ans);
        tm.erase(mit);
        stk.erase(it);
        return ans;
    }

    int top() {
        return stk.back();
    }

    int peekMax() {
        return tm.rbegin()->first;
    }

    int popMax() {
        auto mit = --tm.end();
        auto it = mit->second;
        int ans = *it;
        tm.erase(mit);
        stk.erase(it);
        return ans;
    }

private:
    multimap<int, list<int>::iterator> tm;
    list<int> stk;
};

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack* obj = new MaxStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->peekMax();
 * int param_5 = obj->popMax();
 */
```

<!-- tabs:end -->

<!-- end -->

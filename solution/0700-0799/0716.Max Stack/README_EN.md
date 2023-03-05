# [716. Max Stack](https://leetcode.com/problems/max-stack)

[中文文档](/solution/0700-0799/0716.Max%20Stack/README.md)

## Description

<p>Design a max stack data structure that supports the stack operations and supports finding the stack&#39;s maximum element.</p>

<p>Implement the <code>MaxStack</code> class:</p>

<ul>
	<li><code>MaxStack()</code> Initializes the stack object.</li>
	<li><code>void push(int x)</code> Pushes element <code>x</code> onto the stack.</li>
	<li><code>int pop()</code> Removes the element on top of the stack and returns it.</li>
	<li><code>int top()</code> Gets the element on the top of the stack without removing it.</li>
	<li><code>int peekMax()</code> Retrieves the maximum element in the stack without removing it.</li>
	<li><code>int popMax()</code> Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the <strong>top-most</strong> one.</li>
</ul>

<p>You must come up with a solution that supports <code>O(1)</code> for each <code>top</code> call and <code>O(logn)</code> for each other call.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MaxStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;top&quot;, &quot;popMax&quot;, &quot;top&quot;, &quot;peekMax&quot;, &quot;pop&quot;, &quot;top&quot;]
[[], [5], [1], [5], [], [], [], [], [], []]
<strong>Output</strong>
[null, null, null, null, 5, 5, 1, 5, 1, 5]

<strong>Explanation</strong>
MaxStack stk = new MaxStack();
stk.push(5);   // [<strong><u>5</u></strong>] the top of the stack and the maximum number is 5.
stk.push(1);   // [<u>5</u>, <strong>1</strong>] the top of the stack is 1, but the maximum is 5.
stk.push(5);   // [5, 1, <strong><u>5</u></strong>] the top of the stack is 5, which is also the maximum, because it is the top most one.
stk.top();     // return 5, [5, 1, <strong><u>5</u></strong>] the stack did not change.
stk.popMax();  // return 5, [<u>5</u>, <strong>1</strong>] the stack is changed now, and the top is different from the max.
stk.top();     // return 1, [<u>5</u>, <strong>1</strong>] the stack did not change.
stk.peekMax(); // return 5, [<u>5</u>, <strong>1</strong>] the stack did not change.
stk.pop();     // return 1, [<strong><u>5</u></strong>] the top of the stack and the max element is now 5.
stk.top();     // return 5, [<strong><u>5</u></strong>] the stack did not change.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>7</sup> &lt;= x &lt;= 10<sup>7</sup></code></li>
	<li>At most <code>10<sup>5</sup></code>&nbsp;calls will be made to <code>push</code>, <code>pop</code>, <code>top</code>, <code>peekMax</code>, and <code>popMax</code>.</li>
	<li>There will be <strong>at least one element</strong> in the stack when <code>pop</code>, <code>top</code>, <code>peekMax</code>, or <code>popMax</code> is called.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **...**

```

```

<!-- tabs:end -->

# [03.05. Sort of Stacks](https://leetcode-cn.com/problems/sort-of-stacks-lcci)

## Description
<p>Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the following operations: <code>push</code>, <code>pop</code>, <code>peek</code>, and <code>isEmpty</code>. When the stack is empty, <code>peek</code> should return -1.</p>



<p><strong>Example1:</strong></p>



<pre>

<strong> Input</strong>: 

[&quot;SortedStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;peek&quot;, &quot;pop&quot;, &quot;peek&quot;]

[[], [1], [2], [], [], []]

<strong> Output</strong>: 

[null,null,null,1,null,2]

</pre>



<p><strong>Example2:</strong></p>



<pre>

<strong> Input</strong>:  

[&quot;SortedStack&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;isEmpty&quot;]

[[], [], [], [1], [], []]

<strong> Output</strong>: 

[null,null,null,null,null,true]

</pre>



<p><strong>Note:</strong></p>



<ol>
	<li>The total number of elements in the stack is within the range [0, 5000].</li>
</ol>




## Solutions


### Python3

```python
class SortedStack:

    def __init__(self):
        self.s = []

    def push(self, val: int) -> None:
        t = []
        while not self.isEmpty() and self.s[-1] < val:
            t.append(self.s.pop())
        self.s.append(val)
        while len(t) > 0:
            self.s.append(t.pop())

    def pop(self) -> None:
        if not self.isEmpty():
            self.s.pop()

    def peek(self) -> int:
        return -1 if self.isEmpty() else self.s[-1]

    def isEmpty(self) -> bool:
        return len(self.s) == 0
```

### Java

```java
class SortedStack {
    private Stack<Integer> s;
    public SortedStack() {
        s = new Stack<>();
    }
    
    public void push(int val) {
        Stack<Integer> t = new Stack<>();
        while (!isEmpty() && s.peek() < val) {
            t.push(s.pop());
        }
        s.push(val);
        while (!t.isEmpty()) {
            s.push(t.pop());
        }
    }
    
    public void pop() {
        if (!isEmpty()) {
            s.pop();
        }
    }
    
    public int peek() {
        return isEmpty() ? -1 : s.peek();
    }
    
    public boolean isEmpty() {
        return s.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
```

### ...
```

```

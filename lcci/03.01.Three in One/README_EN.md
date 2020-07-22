# [03.01. Three in One](https://leetcode-cn.com/problems/three-in-one-lcci)

[中文文档](/lcci/03.01.Three%20in%20One/README.md)

## Description
<p>Describe how you could use a single array to implement three stacks.</p>



<p>Yout should implement&nbsp;<code>push(stackNum, value)</code>、<code>pop(stackNum)</code>、<code>isEmpty(stackNum)</code>、<code>peek(stackNum)</code>&nbsp;methods.&nbsp;<code>stackNum<font face="sans-serif, Arial, Verdana, Trebuchet MS">&nbsp;</font></code><font face="sans-serif, Arial, Verdana, Trebuchet MS">is the index of the stack.&nbsp;</font><code>value</code>&nbsp;is the value that pushed to the stack.</p>



<p>The constructor requires a&nbsp;<code>stackSize</code>&nbsp;parameter, which represents the size of each stack.</p>



<p><strong>Example1:</strong></p>



<pre>

<strong> Input</strong>: 

[&quot;TripleInOne&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;isEmpty&quot;]

[[1], [0, 1], [0, 2], [0], [0], [0], [0]]

<strong> Output</strong>: 

[null, null, null, 1, -1, -1, true]

<b>Explanation</b>: When the stack is empty, `pop, peek` return -1. When the stack is full, `push` does nothing.

</pre>



<p><strong>Example2:</strong></p>



<pre>

<strong> Input</strong>: 

[&quot;TripleInOne&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;peek&quot;]

[[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]

<strong> Output</strong>: 

[null, null, null, null, 2, 1, -1, -1]

</pre>




## Solutions


<!-- tabs:start -->

### **Python3**

```python
class TripleInOne:

    def __init__(self, stackSize: int):
        self._capacity = stackSize
        self._s = [[], [], []]

    def push(self, stackNum: int, value: int) -> None:
        if len(self._s[stackNum]) < self._capacity:
            self._s[stackNum].append(value)

    def pop(self, stackNum: int) -> int:
        return -1 if self.isEmpty(stackNum) else self._s[stackNum].pop()

    def peek(self, stackNum: int) -> int:
        return -1 if self.isEmpty(stackNum) else self._s[stackNum][-1]

    def isEmpty(self, stackNum: int) -> bool:
        return len(self._s[stackNum]) == 0


# Your TripleInOne object will be instantiated and called as such:
# obj = TripleInOne(stackSize)
# obj.push(stackNum,value)
# param_2 = obj.pop(stackNum)
# param_3 = obj.peek(stackNum)
# param_4 = obj.isEmpty(stackNum)
```

### **Java**

```java
class TripleInOne {
    private int[] s;
    private int capacity;

    public TripleInOne(int stackSize) {
        s = new int[stackSize * 3 + 3];
        capacity = stackSize;
    }
    
    public void push(int stackNum, int value) {
        if (s[stackNum + 3 * capacity] < capacity) {
            s[s[stackNum + 3 * capacity] * 3 + stackNum] = value;
            ++s[stackNum + 3 * capacity];
        }
    }
    
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        --s[stackNum + 3 * capacity];
        return s[s[stackNum + 3 * capacity] * 3 + stackNum];
    }
    
    public int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : s[(s[stackNum + 3 * capacity] - 1) * 3 + stackNum];
    }
    
    public boolean isEmpty(int stackNum) {
        return s[stackNum + 3 * capacity] == 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
```

### **...**
```

```

<!-- tabs:end -->
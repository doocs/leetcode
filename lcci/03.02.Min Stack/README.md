# [面试题 03.02. 栈的最小值](https://leetcode-cn.com/problems/min-stack-lcci)

[English Version](/lcci/03.02.Min%20Stack/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。</p><br><p><strong>示例：</strong><pre>MinStack minStack = new MinStack();<br>minStack.push(-2);<br>minStack.push(0);<br>minStack.push(-3);<br>minStack.getMin();   --> 返回 -3.<br>minStack.pop();<br>minStack.top();      --> 返回 0.<br>minStack.getMin();   --> 返回 -2.</pre></p>

## 解法
<!-- 这里可写通用的实现逻辑 -->
利用辅助栈存放栈的最小元素。


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self._s1 = []
        self._s2 = []


    def push(self, x: int) -> None:
        self._s1.append(x)
        self._s2.append(x if len(self._s2) == 0 or self._s2[-1] >= x else self._s2[-1])

    def pop(self) -> None:
        self._s1.pop()
        self._s2.pop()

    def top(self) -> int:
        return self._s1[-1]

    def getMin(self) -> int:
        return self._s2[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MinStack {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** initialize your data structure here. */
    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
        s2.push(s2.empty() || s2.peek() >= x ? x : s2.peek());
    }
    
    public void pop() {
        s1.pop();
        s2.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.empty() ? -1 : s2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

### **...**
```

```

<!-- tabs:end -->
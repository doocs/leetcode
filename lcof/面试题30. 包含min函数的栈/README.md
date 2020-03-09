# [面试题30. 包含min函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

## 题目描述
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

**示例:**

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

**提示：**

- 各函数的调用总次数不超过 20000 次

## 解法
### Python3
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
        self._s2.append(x if len(self._s2) == 0 or self._s2[-1] > x else self._s2[-1])

    def pop(self) -> None:
        self._s1.pop()
        self._s2.pop()

    def top(self) -> int:
        return self._s1[-1]

    def min(self) -> int:
        return self._s2[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.min()
```

### Java
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
        s2.push((s2.empty() || s2.peek() > x) ? x : s2.peek());
    }
    
    public void pop() {
        s1.pop();
        s2.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int min() {
        return s2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
```

### ...
```

```

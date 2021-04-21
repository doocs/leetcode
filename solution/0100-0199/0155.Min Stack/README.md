# [155. 最小栈](https://leetcode-cn.com/problems/min-stack)

[English Version](/solution/0100-0199/0155.Min%20Stack/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个支持 <code>push</code> ，<code>pop</code> ，<code>top</code> 操作，并能在常数时间内检索到最小元素的栈。</p>

<ul>
	<li><code>push(x)</code> &mdash;&mdash; 将元素 x 推入栈中。</li>
	<li><code>pop()</code>&nbsp;&mdash;&mdash; 删除栈顶的元素。</li>
	<li><code>top()</code>&nbsp;&mdash;&mdash; 获取栈顶元素。</li>
	<li><code>getMin()</code> &mdash;&mdash; 检索栈中的最小元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入：</strong>
[&quot;MinStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;getMin&quot;,&quot;pop&quot;,&quot;top&quot;,&quot;getMin&quot;]
[[],[-2],[0],[-3],[],[],[],[]]

<strong>输出：</strong>
[null,null,null,null,-3,null,0,-2]

<strong>解释：</strong>
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.getMin();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>pop</code>、<code>top</code> 和 <code>getMin</code> 操作总是在 <strong>非空栈</strong> 上调用。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.s = []
        self.helper = []


    def push(self, x: int) -> None:
        self.s.append(x)
        element = x if not self.helper or x < self.helper[-1] else self.helper[-1]
        self.helper.append(element)

    def pop(self) -> None:
        self.s.pop()
        self.helper.pop()

    def top(self) -> int:
        return self.s[-1]

    def getMin(self) -> int:
        return self.helper[-1]


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

    private Deque<Integer> s;
    private Deque<Integer> helper;

    /** initialize your data structure here. */
    public MinStack() {
        s = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }

    public void push(int x) {
        s.push(x);
        int element = helper.isEmpty() || x < helper.peek() ? x : helper.peek();
        helper.push(element);
    }

    public void pop() {
        s.pop();
        helper.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return helper.peek();
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

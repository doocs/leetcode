# [1381. 设计一个支持增量操作的栈](https://leetcode.cn/problems/design-a-stack-with-increment-operation)

[English Version](/solution/1300-1399/1381.Design%20a%20Stack%20With%20Increment%20Operation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个支持对其元素进行增量操作的栈。</p>

<p>实现自定义栈类 <code>CustomStack</code> ：</p>

<ul>
	<li><code>CustomStack(int maxSize)</code>：用 <code>maxSize</code> 初始化对象，<code>maxSize</code> 是栈中最多能容纳的元素数量。</li>
	<li><code>void push(int x)</code>：如果栈还未增长到 <code>maxSize</code> ，就将 <code>x</code> 添加到栈顶。</li>
	<li><code>int pop()</code>：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 <strong>-1</strong> 。</li>
	<li><code>void inc(int k, int val)</code>：栈底的 <code>k</code> 个元素的值都增加 <code>val</code> 。如果栈中元素总数小于 <code>k</code> ，则栈中的所有元素都增加 <code>val</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
<strong>输出：</strong>
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
<strong>解释：</strong>
CustomStack stk = new CustomStack(3); // 栈是空的 []
stk.push(1);                          // 栈变为 [1]
stk.push(2);                          // 栈变为 [1, 2]
stk.pop();                            // 返回 2 --&gt; 返回栈顶值 2，栈变为 [1]
stk.push(2);                          // 栈变为 [1, 2]
stk.push(3);                          // 栈变为 [1, 2, 3]
stk.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
stk.increment(5, 100);                // 栈变为 [101, 102, 103]
stk.increment(2, 100);                // 栈变为 [201, 202, 103]
stk.pop();                            // 返回 103 --&gt; 返回栈顶值 103，栈变为 [201, 202]
stk.pop();                            // 返回 202 --&gt; 返回栈顶值 202，栈变为 [201]
stk.pop();                            // 返回 201 --&gt; 返回栈顶值 201，栈变为 []
stk.pop();                            // 返回 -1 --&gt; 栈为空，返回 -1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= maxSize, x, k &lt;= 1000</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
	<li>每种方法 <code>increment</code>，<code>push</code> 以及 <code>pop</code> 分别最多调用 <code>1000</code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class CustomStack:
    def __init__(self, maxSize: int):
        self.s = [0] * maxSize
        self.t = 0

    def push(self, x: int) -> None:
        if self.t < len(self.s):
            self.s[self.t] = x
            self.t += 1

    def pop(self) -> int:
        if self.t == 0:
            return -1
        self.t -= 1
        return self.s[self.t]

    def increment(self, k: int, val: int) -> None:
        for i in range(min(k, self.t)):
            self.s[i] += val


# Your CustomStack object will be instantiated and called as such:
# obj = CustomStack(maxSize)
# obj.push(x)
# param_2 = obj.pop()
# obj.increment(k,val)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class CustomStack {
    private int[] s;
    private int t;

    public CustomStack(int maxSize) {
        s = new int[maxSize];
    }

    public void push(int x) {
        if (t < s.length) {
            s[t++] = x;
        }
    }

    public int pop() {
        return t == 0 ? -1 : s[--t];
    }

    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, t); ++i) {
            s[i] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
```

### **TypeScript**

```ts
class CustomStack {
    maxSize: number;
    size: number;
    stack: Array<number>;
    constructor(maxSize: number) {
        this.maxSize = maxSize;
        this.size = 0;
        this.stack = [];
    }

    push(x: number): void {
        if (this.size >= this.maxSize) return;
        this.size++;
        this.stack.unshift(x);
    }

    pop(): number {
        if (!this.size) return -1;
        this.size--;
        return this.stack.shift();
    }

    increment(k: number, val: number): void {
        for (let i = Math.max(this.size - k, 0); i < this.size; i++) {
            this.stack[i] = this.stack[i] + val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * var obj = new CustomStack(maxSize)
 * obj.push(x)
 * var param_2 = obj.pop()
 * obj.increment(k,val)
 */
```

### **C++**

```cpp
class CustomStack {
public:
    vector<int> s;
    int t;

    CustomStack(int maxSize) {
        s.resize(maxSize);
        t = 0;
    }

    void push(int x) {
        if (t < s.size()) s[t++] = x;
    }

    int pop() {
        return t == 0 ? -1 : s[--t];
    }

    void increment(int k, int val) {
        for (int i = 0; i < min(k, t); ++i) s[i] += val;
    }
};

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack* obj = new CustomStack(maxSize);
 * obj->push(x);
 * int param_2 = obj->pop();
 * obj->increment(k,val);
 */
```

### **Go**

```go
type CustomStack struct {
	s []int
	t int
}

func Constructor(maxSize int) CustomStack {
	s := make([]int, maxSize)
	return CustomStack{s, 0}
}

func (this *CustomStack) Push(x int) {
	if this.t < len(this.s) {
		this.s[this.t] = x
		this.t++
	}
}

func (this *CustomStack) Pop() int {
	if this.t == 0 {
		return -1
	}
	this.t--
	return this.s[this.t]
}

func (this *CustomStack) Increment(k int, val int) {
	for i := 0; i < k && i < this.t; i++ {
		this.s[i] += val
	}
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * obj := Constructor(maxSize);
 * obj.Push(x);
 * param_2 := obj.Pop();
 * obj.Increment(k,val);
 */
```

### **...**

```

```

<!-- tabs:end -->

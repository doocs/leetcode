# [1381. Design a Stack With Increment Operation](https://leetcode.com/problems/design-a-stack-with-increment-operation)

[中文文档](/solution/1300-1399/1381.Design%20a%20Stack%20With%20Increment%20Operation/README.md)

## Description

<p>Design a stack which supports the following operations.</p>

<p>Implement the <code>CustomStack</code> class:</p>

<ul>
	<li><code>CustomStack(int maxSize)</code> Initializes the object with <code>maxSize</code> which is the maximum number of elements in the stack or do nothing if the stack reached the <code>maxSize</code>.</li>
	<li><code>void push(int x)</code>&nbsp;Adds <code>x</code> to the top of the stack if the stack hasn&#39;t reached the <code>maxSize</code>.</li>
	<li><code>int pop()</code>&nbsp;Pops and returns the top of stack or <strong>-1</strong> if the stack is empty.</li>
	<li><code>void inc(int k, int val)</code> Increments the bottom <code>k</code> elements of the stack by <code>val</code>. If there are less than <code>k</code> elements in the stack, just increment all the elements in the stack.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;CustomStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;pop&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;increment&quot;,&quot;increment&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
<strong>Output</strong>
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
<strong>Explanation</strong>
CustomStack customStack = new CustomStack(3); // Stack is Empty []
customStack.push(1);                          // stack becomes [1]
customStack.push(2);                          // stack becomes [1, 2]
customStack.pop();                            // return 2 --&gt; Return top of the stack 2, stack becomes [1]
customStack.push(2);                          // stack becomes [1, 2]
customStack.push(3);                          // stack becomes [1, 2, 3]
customStack.push(4);                          // stack still [1, 2, 3], Don&#39;t add another elements as size is 4
customStack.increment(5, 100);                // stack becomes [101, 102, 103]
customStack.increment(2, 100);                // stack becomes [201, 202, 103]
customStack.pop();                            // return 103 --&gt; Return top of the stack 103, stack becomes [201, 202]
customStack.pop();                            // return 202 --&gt; Return top of the stack 102, stack becomes [201]
customStack.pop();                            // return 201 --&gt; Return top of the stack 101, stack becomes []
customStack.pop();                            // return -1 --&gt; Stack is empty return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxSize &lt;= 1000</code></li>
	<li><code>1 &lt;= x &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
	<li>At most&nbsp;<code>1000</code>&nbsp;calls will be made to each method of <code>increment</code>, <code>push</code> and <code>pop</code> each separately.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

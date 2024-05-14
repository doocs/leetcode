# [1381. Design a Stack With Increment Operation](https://leetcode.com/problems/design-a-stack-with-increment-operation)

[中文文档](/solution/1300-1399/1381.Design%20a%20Stack%20With%20Increment%20Operation/README.md)

<!-- tags:Stack,Design,Array -->

<!-- difficulty:Medium -->

## Description

<p>Design a stack that supports increment operations on its elements.</p>

<p>Implement the <code>CustomStack</code> class:</p>

<ul>
	<li><code>CustomStack(int maxSize)</code> Initializes the object with <code>maxSize</code> which is the maximum number of elements in the stack.</li>
	<li><code>void push(int x)</code> Adds <code>x</code> to the top of the stack if the stack has not reached the <code>maxSize</code>.</li>
	<li><code>int pop()</code> Pops and returns the top of the stack or <code>-1</code> if the stack is empty.</li>
	<li><code>void inc(int k, int val)</code> Increments the bottom <code>k</code> elements of the stack by <code>val</code>. If there are less than <code>k</code> elements in the stack, increment all the elements in the stack.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;CustomStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;pop&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;increment&quot;,&quot;increment&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
<strong>Output</strong>
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
<strong>Explanation</strong>
CustomStack stk = new CustomStack(3); // Stack is Empty []
stk.push(1);                          // stack becomes [1]
stk.push(2);                          // stack becomes [1, 2]
stk.pop();                            // return 2 --&gt; Return top of the stack 2, stack becomes [1]
stk.push(2);                          // stack becomes [1, 2]
stk.push(3);                          // stack becomes [1, 2, 3]
stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
stk.increment(5, 100);                // stack becomes [101, 102, 103]
stk.increment(2, 100);                // stack becomes [201, 202, 103]
stk.pop();                            // return 103 --&gt; Return top of the stack 103, stack becomes [201, 202]
stk.pop();                            // return 202 --&gt; Return top of the stack 202, stack becomes [201]
stk.pop();                            // return 201 --&gt; Return top of the stack 201, stack becomes []
stk.pop();                            // return -1 --&gt; Stack is empty return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxSize, x, k &lt;= 1000</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
	<li>At most <code>1000</code> calls will be made to each method of <code>increment</code>, <code>push</code> and <code>pop</code> each separately.</li>
</ul>

## Solutions

### Solution 1: Array Simulation

We can use an array $stk$ to simulate the stack, and an integer $i$ to represent the position of the next element to be pushed into the stack. In addition, we need another array $add$ to record the cumulative increment value at each position.

When calling $push(x)$, if $i < maxSize$, we put $x$ into $stk[i]$ and increment $i$ by one.

When calling $pop()$, if $i \leq 0$, it means the stack is empty, so we return $-1$. Otherwise, we decrement $i$ by one, and the answer is $stk[i] + add[i]$. Then we add $add[i]$ to $add[i - 1]$, and set $add[i]$ to zero. Finally, we return the answer.

When calling $increment(k, val)$, if $i > 0$, we add $val$ to $add[\min(i, k) - 1]$.

The time complexity is $O(1)$, and the space complexity is $O(n)$. Where $n$ is the maximum capacity of the stack.

<!-- tabs:start -->

```python
class CustomStack:
    def __init__(self, maxSize: int):
        self.stk = [0] * maxSize
        self.add = [0] * maxSize
        self.i = 0

    def push(self, x: int) -> None:
        if self.i < len(self.stk):
            self.stk[self.i] = x
            self.i += 1

    def pop(self) -> int:
        if self.i <= 0:
            return -1
        self.i -= 1
        ans = self.stk[self.i] + self.add[self.i]
        if self.i > 0:
            self.add[self.i - 1] += self.add[self.i]
        self.add[self.i] = 0
        return ans

    def increment(self, k: int, val: int) -> None:
        i = min(k, self.i) - 1
        if i >= 0:
            self.add[i] += val


# Your CustomStack object will be instantiated and called as such:
# obj = CustomStack(maxSize)
# obj.push(x)
# param_2 = obj.pop()
# obj.increment(k,val)
```

```java
class CustomStack {
    private int[] stk;
    private int[] add;
    private int i;

    public CustomStack(int maxSize) {
        stk = new int[maxSize];
        add = new int[maxSize];
    }

    public void push(int x) {
        if (i < stk.length) {
            stk[i++] = x;
        }
    }

    public int pop() {
        if (i <= 0) {
            return -1;
        }
        int ans = stk[--i] + add[i];
        if (i > 0) {
            add[i - 1] += add[i];
        }
        add[i] = 0;
        return ans;
    }

    public void increment(int k, int val) {
        if (i > 0) {
            add[Math.min(i, k) - 1] += val;
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

```cpp
class CustomStack {
public:
    CustomStack(int maxSize) {
        stk.resize(maxSize);
        add.resize(maxSize);
        i = 0;
    }

    void push(int x) {
        if (i < stk.size()) {
            stk[i++] = x;
        }
    }

    int pop() {
        if (i <= 0) {
            return -1;
        }
        int ans = stk[--i] + add[i];
        if (i > 0) {
            add[i - 1] += add[i];
        }
        add[i] = 0;
        return ans;
    }

    void increment(int k, int val) {
        if (i > 0) {
            add[min(k, i) - 1] += val;
        }
    }

private:
    vector<int> stk;
    vector<int> add;
    int i;
};

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack* obj = new CustomStack(maxSize);
 * obj->push(x);
 * int param_2 = obj->pop();
 * obj->increment(k,val);
 */
```

```go
type CustomStack struct {
	stk []int
	add []int
	i   int
}

func Constructor(maxSize int) CustomStack {
	return CustomStack{make([]int, maxSize), make([]int, maxSize), 0}
}

func (this *CustomStack) Push(x int) {
	if this.i < len(this.stk) {
		this.stk[this.i] = x
		this.i++
	}
}

func (this *CustomStack) Pop() int {
	if this.i <= 0 {
		return -1
	}
	this.i--
	ans := this.stk[this.i] + this.add[this.i]
	if this.i > 0 {
		this.add[this.i-1] += this.add[this.i]
	}
	this.add[this.i] = 0
	return ans
}

func (this *CustomStack) Increment(k int, val int) {
	if this.i > 0 {
		this.add[min(k, this.i)-1] += val
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

```ts
class CustomStack {
    private stk: number[];
    private add: number[];
    private i: number;

    constructor(maxSize: number) {
        this.stk = Array(maxSize).fill(0);
        this.add = Array(maxSize).fill(0);
        this.i = 0;
    }

    push(x: number): void {
        if (this.i < this.stk.length) {
            this.stk[this.i++] = x;
        }
    }

    pop(): number {
        if (this.i <= 0) {
            return -1;
        }
        const ans = this.stk[--this.i] + this.add[this.i];
        if (this.i > 0) {
            this.add[this.i - 1] += this.add[this.i];
        }
        this.add[this.i] = 0;
        return ans;
    }

    increment(k: number, val: number): void {
        if (this.i > 0) {
            this.add[Math.min(this.i, k) - 1] += val;
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

<!-- tabs:end -->

<!-- end -->

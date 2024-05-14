---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1381.Design%20a%20Stack%20With%20Increment%20Operation/README.md
rating: 1285
tags:
    - 栈
    - 设计
    - 数组
---

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

### 方法一：数组模拟

我们可以用一个数组 $stk$ 来模拟栈，用一个整数 $i$ 表示下一个入栈的元素位置。另外，我们还需要一个数组 $add$ 来记录每个位置上的增量累加值。

调用 $push(x)$ 时，如果 $i \lt maxSize$，我们将 $x$ 放入 $stk[i]$ 中，并将 $i$ 加一。

调用 $pop()$ 时，如果 $i \leq 0$，说明栈为空，返回 $-1$。否则我们将 $i$ 减一，答案为 $stk[i] + add[i]$，然后我们将 $add[i - 1]$ 加上 $add[i]$，并将 $add[i]$ 清零。最后返回答案。

调用 $increment(k, val)$ 时，如果 $i \gt 0$，我们将 $add[\min(i, k) - 1]$ 加上 $val$。

时间复杂度 $O(1)$，空间复杂度 $O(n)$。其中 $n$ 是栈的最大容量。

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

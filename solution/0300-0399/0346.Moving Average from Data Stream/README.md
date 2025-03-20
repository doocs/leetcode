---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0346.Moving%20Average%20from%20Data%20Stream/README.md
tags:
    - 设计
    - 队列
    - 数组
    - 数据流
---

<!-- problem:start -->

# [346. 数据流中的移动平均值 🔒](https://leetcode.cn/problems/moving-average-from-data-stream)

[English Version](/solution/0300-0399/0346.Moving%20Average%20from%20Data%20Stream/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。</p>

<p>实现 <code>MovingAverage</code> 类：</p>

<ul>
	<li><code>MovingAverage(int size)</code> 用窗口大小 <code>size</code> 初始化对象。</li>
	<li><code>double next(int val)</code> 计算并返回数据流中最后 <code>size</code> 个值的移动平均值。</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
<strong>输出：</strong>
[null, 1.0, 5.5, 4.66667, 6.0]

<strong>解释：</strong>
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // 返回 1.0 = 1 / 1
movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= size <= 1000</code></li>
	<li><code>-10<sup>5</sup> <= val <= 10<sup>5</sup></code></li>
	<li>最多调用 <code>next</code> 方法 <code>10<sup>4</sup></code> 次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：循环数组

我们定义一个变量 $\textit{s}$，用于计算当前最后 $\textit{size}$ 个元素的和，用一个变量 $\textit{cnt}$ 记录当前元素的总数。另外，我们用一个长度为 $\textit{size}$ 的数组 $\textit{data}$ 记录每个位置的元素对应的值。

调用 $\textit{next}$ 函数时，我们先计算出 $\textit{val}$ 要存放的下标 $i$，然后我们更新元素和 $s$，并且将下标 $i$ 处的值设置为 $\textit{val}$，同时将元素的个数加一。最后，我们返回 $\frac{s}{\min(\textit{cnt}, \textit{size})}$ 的值即可。

时间复杂度 $O(1)$，空间复杂度 $O(n)$，其中 $n$ 是题目给定的整数 $\textit{size}$。

<!-- tabs:start -->

#### Python3

```python
class MovingAverage:

    def __init__(self, size: int):
        self.s = 0
        self.data = [0] * size
        self.cnt = 0

    def next(self, val: int) -> float:
        i = self.cnt % len(self.data)
        self.s += val - self.data[i]
        self.data[i] = val
        self.cnt += 1
        return self.s / min(self.cnt, len(self.data))


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
```

#### Java

```java
class MovingAverage {
    private int s;
    private int cnt;
    private int[] data;

    public MovingAverage(int size) {
        data = new int[size];
    }

    public double next(int val) {
        int i = cnt % data.length;
        s += val - data[i];
        data[i] = val;
        ++cnt;
        return s * 1.0 / Math.min(cnt, data.length);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```

#### C++

```cpp
class MovingAverage {
public:
    MovingAverage(int size) {
        data.resize(size);
    }

    double next(int val) {
        int i = cnt % data.size();
        s += val - data[i];
        data[i] = val;
        ++cnt;
        return s * 1.0 / min(cnt, (int) data.size());
    }

private:
    int s = 0;
    int cnt = 0;
    vector<int> data;
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage* obj = new MovingAverage(size);
 * double param_1 = obj->next(val);
 */
```

#### Go

```go
type MovingAverage struct {
	s    int
	cnt  int
	data []int
}

func Constructor(size int) MovingAverage {
	return MovingAverage{
		data: make([]int, size),
	}
}

func (this *MovingAverage) Next(val int) float64 {
	i := this.cnt % len(this.data)
	this.s += val - this.data[i]
	this.data[i] = val
	this.cnt++
	return float64(this.s) / float64(min(this.cnt, len(this.data)))
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * obj := Constructor(size);
 * param_1 := obj.Next(val);
 */
```

#### TypeScript

```ts
class MovingAverage {
    private s: number = 0;
    private cnt: number = 0;
    private data: number[];

    constructor(size: number) {
        this.data = Array(size).fill(0);
    }

    next(val: number): number {
        const i = this.cnt % this.data.length;
        this.s += val - this.data[i];
        this.data[i] = val;
        this.cnt++;
        return this.s / Math.min(this.cnt, this.data.length);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = new MovingAverage(size)
 * var param_1 = obj.next(val)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：队列

我们可以使用一个队列 $\textit{q}$ 来存储最后 $\textit{size}$ 个元素，同时用一个变量 $\textit{s}$ 来记录这 $\textit{size}$ 个元素的和。

在调用 $\textit{next}$ 函数时，我们首先判断队列 $\textit{q}$ 的长度是否等于 $\textit{size}$，如果等于 $\textit{size}$，则将队列 $\textit{q}$ 的头部元素出队，并且更新 $\textit{s}$ 的值。然后将 $\textit{val}$ 入队，并且更新 $\textit{s}$ 的值。最后返回 $\frac{s}{\text{len}(q)}$ 的值即可。

时间复杂度 $O(1)$，空间复杂度 $O(n)$，其中 $n$ 是题目给定的整数 $\textit{size}$。

<!-- tabs:start -->

#### Python3

```python
class MovingAverage:
    def __init__(self, size: int):
        self.n = size
        self.s = 0
        self.q = deque()

    def next(self, val: int) -> float:
        if len(self.q) == self.n:
            self.s -= self.q.popleft()
        self.q.append(val)
        self.s += val
        return self.s / len(self.q)


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
```

#### Java

```java
class MovingAverage {
    private Deque<Integer> q = new ArrayDeque<>();
    private int n;
    private int s;

    public MovingAverage(int size) {
        n = size;
    }

    public double next(int val) {
        if (q.size() == n) {
            s -= q.pollFirst();
        }
        q.offer(val);
        s += val;
        return s * 1.0 / q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```

#### C++

```cpp
class MovingAverage {
public:
    MovingAverage(int size) {
        n = size;
    }

    double next(int val) {
        if (q.size() == n) {
            s -= q.front();
            q.pop();
        }
        q.push(val);
        s += val;
        return (double) s / q.size();
    }

private:
    queue<int> q;
    int s = 0;
    int n;
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage* obj = new MovingAverage(size);
 * double param_1 = obj->next(val);
 */
```

#### Go

```go
type MovingAverage struct {
	q []int
	s int
	n int
}

func Constructor(size int) MovingAverage {
	return MovingAverage{n: size}
}

func (this *MovingAverage) Next(val int) float64 {
	if len(this.q) == this.n {
		this.s -= this.q[0]
		this.q = this.q[1:]
	}
	this.q = append(this.q, val)
	this.s += val
	return float64(this.s) / float64(len(this.q))
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * obj := Constructor(size);
 * param_1 := obj.Next(val);
 */
```

#### TypeScript

```ts
class MovingAverage {
    private q: number[] = [];
    private s: number = 0;
    private n: number;

    constructor(size: number) {
        this.n = size;
    }

    next(val: number): number {
        if (this.q.length === this.n) {
            this.s -= this.q.shift()!;
        }
        this.q.push(val);
        this.s += val;
        return this.s / this.q.length;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = new MovingAverage(size)
 * var param_1 = obj.next(val)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

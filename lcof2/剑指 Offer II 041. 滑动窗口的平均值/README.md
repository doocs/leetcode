---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20041.%20%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E7%9A%84%E5%B9%B3%E5%9D%87%E5%80%BC/README.md
---

<!-- problem:start -->

# [剑指 Offer II 041. 滑动窗口的平均值](https://leetcode.cn/problems/qIsx9U)

## 题目描述

<!-- description:start -->

<p>给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。</p>

<p>实现 <code>MovingAverage</code> 类：</p>

<ul>
	<li><code>MovingAverage(int size)</code> 用窗口大小 <code>size</code> 初始化对象。</li>
	<li><code>double next(int val)</code>&nbsp;成员函数 <code>next</code>&nbsp;每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 <code>size</code> 个值的移动平均值，即滑动窗口里所有数字的平均值。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
inputs = [&quot;MovingAverage&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;]
inputs = [[3], [1], [10], [3], [5]]
<strong>输出：</strong>
[null, 1.0, 5.5, 4.66667, 6.0]

<strong>解释：</strong>
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // 返回 1.0 = 1 / 1
movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= size &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= val &lt;= 10<sup>5</sup></code></li>
	<li>最多调用 <code>next</code> 方法 <code>10<sup>4</sup></code> 次</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 346&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/moving-average-from-data-stream/">https://leetcode.cn/problems/moving-average-from-data-stream/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：循环数组

<!-- tabs:start -->

#### Python3

```python
class MovingAverage:
    def __init__(self, size: int):
        self.arr = [0] * size
        self.s = 0
        self.cnt = 0

    def next(self, val: int) -> float:
        idx = self.cnt % len(self.arr)
        self.s += val - self.arr[idx]
        self.arr[idx] = val
        self.cnt += 1
        return self.s / min(self.cnt, len(self.arr))


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
```

#### Java

```java
class MovingAverage {
    private int[] arr;
    private int s;
    private int cnt;

    public MovingAverage(int size) {
        arr = new int[size];
    }

    public double next(int val) {
        int idx = cnt % arr.length;
        s += val - arr[idx];
        arr[idx] = val;
        ++cnt;
        return s * 1.0 / Math.min(cnt, arr.length);
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
        arr.resize(size);
    }

    double next(int val) {
        int idx = cnt % arr.size();
        s += val - arr[idx];
        arr[idx] = val;
        ++cnt;
        return (double) s / min(cnt, (int) arr.size());
    }

private:
    vector<int> arr;
    int cnt = 0;
    int s = 0;
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
	arr []int
	cnt int
	s   int
}

func Constructor(size int) MovingAverage {
	arr := make([]int, size)
	return MovingAverage{arr, 0, 0}
}

func (this *MovingAverage) Next(val int) float64 {
	idx := this.cnt % len(this.arr)
	this.s += val - this.arr[idx]
	this.arr[idx] = val
	this.cnt++
	return float64(this.s) / float64(min(this.cnt, len(this.arr)))
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * obj := Constructor(size);
 * param_1 := obj.Next(val);
 */
```

#### Swift

```swift
class MovingAverage {
    private var arr: [Int]
    private var s: Int
    private var cnt: Int

    init(_ size: Int) {
        arr = [Int](repeating: 0, count: size)
        s = 0
        cnt = 0
    }

    func next(_ val: Int) -> Double {
        let idx = cnt % arr.count
        s += val - arr[idx]
        arr[idx] = val
        cnt += 1
        return Double(s) / Double(min(cnt, arr.count))
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * let obj = MovingAverage(size)
 * let param_1 = obj.next(val)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：队列

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

# [346. Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream)

[中文文档](/solution/0300-0399/0346.Moving%20Average%20from%20Data%20Stream/README.md)

## Description

<p>Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.</p>

<p>Implement the&nbsp;<code>MovingAverage</code> class:</p>

<ul>
	<li><code>MovingAverage(int size)</code> Initializes&nbsp;the object with the size of the window <code>size</code>.</li>
	<li><code>double next(int val)</code> Returns the moving average of the last <code>size</code> values of the stream.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MovingAverage&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;]
[[3], [1], [10], [3], [5]]
<strong>Output</strong>
[null, 1.0, 5.5, 4.66667, 6.0]

<strong>Explanation</strong>
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= size &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= val &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>next</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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
        return (double)s / min(cnt, (int)arr.size());
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

```cpp
class MovingAverage {
public:
    MovingAverage(int size) {
        n = size;
    }

    double next(int val) {
        if (q.size() == n)
        {
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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * obj := Constructor(size);
 * param_1 := obj.Next(val);
 */
```

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

### **...**

```

```

<!-- tabs:end -->

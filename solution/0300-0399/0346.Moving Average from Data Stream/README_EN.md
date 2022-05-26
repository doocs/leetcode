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
<p><strong>Example 1:</strong></p>

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
        """
        Initialize your data structure here.
        """
        self.size = size
        self.data = [0] * size
        self.sum = 0
        self.count = 0

    def next(self, val: int) -> float:
        idx = self.count % self.size
        old_val = self.data[idx]
        self.data[idx] = val
        self.sum += val - old_val
        self.count += 1
        return self.sum / min(self.count, self.size)

# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
```

### **Java**

```java
class MovingAverage {
    private int size;
    private int[] data;
    private int sum;
    private int count;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.data = new int[size];
    }

    public double next(int val) {
        int idx = count % size;
        int oldVal = data[idx];
        data[idx] = val;
        sum += val - oldVal;
        ++count;
        return sum * 1.0 / Math.min(count, size);
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
    /** Initialize your data structure here. */
    MovingAverage(int size) {
        this->size = size;
        data.resize(size);
    }

    double next(int val) {
        int idx = count % size;
        int oldVal = data[idx];
        data[idx] = val;
        sum += val - oldVal;
        ++count;
        return (double) sum / min(count, size);
    }

private:
    int size = 0;
    vector<int> data;
    int sum = 0;
    int count = 0;
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
	size  int
	data  []int
	sum   int
	count int
}

/** Initialize your data structure here. */
func Constructor(size int) MovingAverage {
	return MovingAverage{
		size:  size,
		data:  make([]int, size),
		sum:   0,
		count: 0,
	}
}

func (this *MovingAverage) Next(val int) float64 {
	idx := this.count % this.size
	oldVal := this.data[idx]
	this.data[idx] = val
	this.sum += val - oldVal
	this.count++
	return float64(this.sum) / float64(min(this.count, this.size))
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

### **...**

```

```

<!-- tabs:end -->

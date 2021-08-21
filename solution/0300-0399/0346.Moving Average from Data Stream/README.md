# [346. 数据流中的移动平均值](https://leetcode-cn.com/problems/moving-average-from-data-stream)

[English Version](/solution/0300-0399/0346.Moving%20Average%20from%20Data%20Stream/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

“循环数组/队列”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

# [2526. Find Consecutive Integers from a Data Stream](https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream)

[中文文档](/solution/2500-2599/2526.Find%20Consecutive%20Integers%20from%20a%20Data%20Stream/README.md)

## Description

<p>For a stream of integers, implement a data structure that checks if the last <code>k</code> integers parsed in the stream are <strong>equal</strong> to <code>value</code>.</p>

<p>Implement the <strong>DataStream</strong> class:</p>

<ul>
	<li><code>DataStream(int value, int k)</code> Initializes the object with an empty integer stream and the two integers <code>value</code> and <code>k</code>.</li>
	<li><code>boolean consec(int num)</code> Adds <code>num</code> to the stream of integers. Returns <code>true</code> if the last <code>k</code> integers are equal to <code>value</code>, and <code>false</code> otherwise. If there are less than <code>k</code> integers, the condition does not hold true, so returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;DataStream&quot;, &quot;consec&quot;, &quot;consec&quot;, &quot;consec&quot;, &quot;consec&quot;]
[[4, 3], [4], [4], [4], [3]]
<strong>Output</strong>
[null, false, false, true, false]

<strong>Explanation</strong>
DataStream dataStream = new DataStream(4, 3); //value = 4, k = 3 
dataStream.consec(4); // Only 1 integer is parsed, so returns False. 
dataStream.consec(4); // Only 2 integers are parsed.
                      // Since 2 is less than k, returns False. 
dataStream.consec(4); // The 3 integers parsed are all equal to value, so returns True. 
dataStream.consec(3); // The last k integers parsed in the stream are [4,4,3].
                      // Since 3 is not equal to value, it returns False.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= value, num &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>consec</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class DataStream:

    def __init__(self, value: int, k: int):
        self.val, self.k = value, k
        self.cnt = 0

    def consec(self, num: int) -> bool:
        self.cnt = 0 if num != self.val else self.cnt + 1
        return self.cnt >= self.k


# Your DataStream object will be instantiated and called as such:
# obj = DataStream(value, k)
# param_1 = obj.consec(num)
```

### **Java**

```java
class DataStream {
    private int cnt;
    private int val;
    private int k;

    public DataStream(int value, int k) {
        val = value;
        this.k = k;
    }

    public boolean consec(int num) {
        cnt = num == val ? cnt + 1 : 0;
        return cnt >= k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */
```

### **C++**

```cpp
class DataStream {
public:
    DataStream(int value, int k) {
        val = value;
        this->k = k;
    }

    bool consec(int num) {
        cnt = num == val ? cnt + 1 : 0;
        return cnt >= k;
    }

private:
    int cnt = 0;
    int val, k;
};

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream* obj = new DataStream(value, k);
 * bool param_1 = obj->consec(num);
 */
```

### **Go**

```go
type DataStream struct {
	val, k, cnt int
}

func Constructor(value int, k int) DataStream {
	return DataStream{value, k, 0}
}

func (this *DataStream) Consec(num int) bool {
	if num == this.val {
		this.cnt++
	} else {
		this.cnt = 0
	}
	return this.cnt >= this.k
}

/**
 * Your DataStream object will be instantiated and called as such:
 * obj := Constructor(value, k);
 * param_1 := obj.Consec(num);
 */
```

### **...**

```

```

<!-- tabs:end -->

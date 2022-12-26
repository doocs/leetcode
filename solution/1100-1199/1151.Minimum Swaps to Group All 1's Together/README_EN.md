# [1151. Minimum Swaps to Group All 1's Together](https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together)

[中文文档](/solution/1100-1199/1151.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together/README.md)

## Description

<p>Given a&nbsp;binary array <code>data</code>, return&nbsp;the minimum number of swaps required to group all <code>1</code>&rsquo;s present in the array together in <strong>any place</strong> in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> data = [1,0,1,0,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There are 3 ways to group all 1&#39;s together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> data = [0,0,0,1,0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since there is only one 1 in the array, no swaps are needed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> data = [1,0,1,0,1,0,0,1,1,0,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= data.length &lt;= 10<sup>5</sup></code></li>
	<li><code>data[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSwaps(self, data: List[int]) -> int:
        k = data.count(1)
        t = sum(data[:k])
        mx = t
        for i in range(k, len(data)):
            t += data[i]
            t -= data[i - k]
            mx = max(mx, t)
        return k - mx
```

### **Java**

```java
class Solution {
    public int minSwaps(int[] data) {
        int k = 0;
        for (int v : data) {
            k += v;
        }
        int t = 0;
        for (int i = 0; i < k; ++i) {
            t += data[i];
        }
        int mx = t;
        for (int i = k; i < data.length; ++i) {
            t += data[i];
            t -= data[i - k];
            mx = Math.max(mx, t);
        }
        return k - mx;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSwaps(vector<int>& data) {
        int k = 0;
        for (int& v : data) {
            k += v;
        }
        int t = 0;
        for (int i = 0; i < k; ++i) {
            t += data[i];
        }
        int mx = t;
        for (int i = k; i < data.size(); ++i) {
            t += data[i];
            t -= data[i - k];
            mx = max(mx, t);
        }
        return k - mx;
    }
};
```

### **Go**

```go
func minSwaps(data []int) int {
	k := 0
	for _, v := range data {
		k += v
	}
	t := 0
	for _, v := range data[:k] {
		t += v
	}
	mx := t
	for i := k; i < len(data); i++ {
		t += data[i]
		t -= data[i-k]
		mx = max(mx, t)
	}
	return k - mx
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->

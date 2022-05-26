# [1014. Best Sightseeing Pair](https://leetcode.com/problems/best-sightseeing-pair)

[中文文档](/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README.md)

## Description

<p>You are given an integer array <code>values</code> where values[i] represents the value of the <code>i<sup>th</sup></code> sightseeing spot. Two sightseeing spots <code>i</code> and <code>j</code> have a <strong>distance</strong> <code>j - i</code> between them.</p>

<p>The score of a pair (<code>i &lt; j</code>) of sightseeing spots is <code>values[i] + values[j] + i - j</code>: the sum of the values of the sightseeing spots, minus the distance between them.</p>

<p>Return <em>the maximum score of a pair of sightseeing spots</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [8,1,5,2,6]
<strong>Output:</strong> 11
<strong>Explanation:</strong> i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [1,2]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= values.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        res, mx = 0, values[0]
        for i in range(1, len(values)):
            res = max(res, values[i] - i + mx)
            mx = max(mx, values[i] + i)
        return res
```

### **Java**

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int res = 0, mx = values[0];
        for (int i = 1; i < values.length; ++i) {
            res = Math.max(res, values[i] - i + mx);
            mx = Math.max(mx, values[i] + i);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int res = 0, mx = values[0];
        for (int i = 1; i < values.size(); ++i) {
            res = max(res, values[i] - i + mx);
            mx = max(mx, values[i] + i);
        }
        return res;
    }
};
```

### **Go**

```go
func maxScoreSightseeingPair(values []int) int {
	res, mx := 0, values[0]
	for i := 1; i < len(values); i++ {
		res = max(res, values[i]-i+mx)
		mx = max(mx, values[i]+i)
	}
	return res
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

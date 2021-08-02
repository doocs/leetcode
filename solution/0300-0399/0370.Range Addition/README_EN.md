# [370. Range Addition](https://leetcode.com/problems/range-addition)

[中文文档](/solution/0300-0399/0370.Range%20Addition/README.md)

## Description

<p>You are given an integer <code>length</code> and an array <code>updates</code> where <code>updates[i] = [startIdx<sub>i</sub>, endIdx<sub>i</sub>, inc<sub>i</sub>]</code>.</p>

<p>You have an array <code>arr</code> of length <code>length</code> with all zeros, and you have some operation to apply on <code>arr</code>. In the <code>i<sup>th</sup></code> operation, you should increment all the elements <code>arr[startIdx<sub>i</sub>], arr[startIdx<sub>i</sub> + 1], ..., arr[endIdx<sub>i</sub>]</code> by <code>inc<sub>i</sub></code>.</p>

<p>Return <code>arr</code> <em>after applying all the</em> <code>updates</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0370.Range%20Addition/images/rangeadd-grid.jpg" style="width: 413px; height: 573px;" />
<pre>
<strong>Input:</strong> length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
<strong>Output:</strong> [-2,0,3,5,3]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
<strong>Output:</strong> [0,-4,2,2,2,4,4,-4,-4,-4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= updates.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= startIdx<sub>i</sub> &lt;= endIdx<sub>i</sub> &lt; length</code></li>
	<li><code>-1000 &lt;= inc<sub>i</sub> &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        delta = [0] * length
        for start, end, inc in updates:
            delta[start] += inc
            if end + 1 < length:
                delta[end + 1] -= inc
        for i in range(1, length):
            delta[i] += delta[i - 1]
        return delta
```

### **Java**

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] delta = new int[length];
        for (int[] e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) {
                delta[e[1] + 1] -= e[2];
            }
        }
        for (int i = 1; i < length; ++i) {
            delta[i] += delta[i - 1];
        }
        return delta;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> delta(length);
        for (auto e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) delta[e[1] + 1] -= e[2];
        }
        for (int i = 1; i < length; ++i) delta[i] += delta[i - 1];
        return delta;
    }
};
```

### **Go**

```go
func getModifiedArray(length int, updates [][]int) []int {
	delta := make([]int, length)
	for _, e := range updates {
		delta[e[0]] += e[2]
		if e[1]+1 < length {
			delta[e[1]+1] -= e[2]
		}
	}
	for i := 1; i < length; i++ {
		delta[i] += delta[i-1]
	}
	return delta
}
```

### **...**

```

```

<!-- tabs:end -->

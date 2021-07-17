# [120. Triangle](https://leetcode.com/problems/triangle)

[中文文档](/solution/0100-0199/0120.Triangle/README.md)

## Description

<p>Given a <code>triangle</code> array, return <em>the minimum path sum from top to bottom</em>.</p>

<p>For each step, you may move to an adjacent number of the row below. More formally, if you are on index <code>i</code> on the current row, you may move to either index <code>i</code> or index <code>i + 1</code> on the next row.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The triangle looks like:
   <u>2</u>
  <u>3</u> 4
 6 <u>5</u> 7
4 <u>1</u> 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> triangle = [[-10]]
<strong>Output:</strong> -10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= triangle.length &lt;= 200</code></li>
	<li><code>triangle[0].length == 1</code></li>
	<li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= triangle[i][j] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you&nbsp;do this using only <code>O(n)</code> extra space, where <code>n</code> is the total number of rows in the triangle?

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        for i in range(1, n):
            for j in range(i + 1):
                mi = float('inf')
                if j > 0:
                    mi = min(mi, triangle[i - 1][j - 1])
                if j < i:
                    mi = min(mi, triangle[i - 1][j])
                triangle[i][j] += mi
        return min(triangle[n - 1])
```

### **Java**

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                int mi = Integer.MAX_VALUE;
                if (j > 0) {
                    mi = Math.min(mi, triangle.get(i - 1).get(j - 1));
                }
                if (j < i) {
                    mi = Math.min(mi, triangle.get(i - 1).get(j));
                }
                triangle.get(i).set(j, triangle.get(i).get(j) + mi);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int val : triangle.get(n - 1)) {
            res = Math.min(res, val);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                int mi = INT_MAX;
                if (j > 0) mi = min(mi, triangle[i - 1][j - 1]);
                if (j < i) mi = min(mi, triangle[i - 1][j]);
                triangle[i][j] += mi;
            }
        }
        int res = INT_MAX;
        for (int& val : triangle[n - 1]) {
            res = min(res, val);
        }
        return res;
    }
};
```

### **Go**

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	for i := 1; i < n; i++ {
		for j := 0; j < i+1; j++ {
			mi := 2000000
			if j > 0 && mi > triangle[i-1][j-1] {
				mi = triangle[i-1][j-1]
			}
			if j < i && mi > triangle[i-1][j] {
				mi = triangle[i-1][j]
			}
			triangle[i][j] += mi
		}
	}

	res := 2000000
	for j := 0; j < n; j++ {
		if res > triangle[n-1][j] {
			res = triangle[n-1][j]
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->

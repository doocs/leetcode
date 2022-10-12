# [120. Triangle](https://leetcode.com/problems/triangle)

[中文文档](/solution/0100-0199/0120.Triangle/README.md)

## Description

<p>Given a <code>triangle</code> array, return <em>the minimum path sum from top to bottom</em>.</p>

<p>For each step, you may move to an adjacent number of the row below. More formally, if you are on index <code>i</code> on the current row, you may move to either index <code>i</code> or index <code>i + 1</code> on the next row.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<p><strong class="example">Example 2:</strong></p>

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
        dp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
        return dp[0][0]
```

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        dp = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
        return dp[0]
```

### **Java**

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        vector<int> dp(n + 1);
        for (int i = n - 1; i >= 0; --i)
            for (int j = 0; j <= i; ++j)
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j];
        return dp[0];
    }
};
```

### **Go**

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	dp := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			dp[j] = min(dp[j], dp[j+1]) + triangle[i][j]
		}
	}
	return dp[0]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    for (let i = n - 2; i >= 0; i--) {
        for (let j = 0; j < i + 1; j++) {
            triangle[i][j] += Math.min(
                triangle[i + 1][j],
                triangle[i + 1][j + 1],
            );
        }
    }
    return triangle[0][0];
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_total(mut triangle: Vec<Vec<i32>>) -> i32 {
        let n = triangle.len();
        for i in (0..n - 1).rev() {
            for j in 0..i + 1 {
                triangle[i][j] += triangle[i + 1][j].min(triangle[i + 1][j + 1]);
            }
        }
        triangle[0][0]
    }
}
```

### **...**

```

```

<!-- tabs:end -->

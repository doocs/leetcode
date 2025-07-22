---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/README_EN.md
rating: 1648
source: Biweekly Contest 59 Q2
tags:
    - Greedy
    - Array
    - Matrix
---

<!-- problem:start -->

# [1975. Maximum Matrix Sum](https://leetcode.com/problems/maximum-matrix-sum)

[中文文档](/solution/1900-1999/1975.Maximum%20Matrix%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>n x n</code> integer <code>matrix</code>. You can do the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any two <strong>adjacent</strong> elements of <code>matrix</code> and <strong>multiply</strong> each of them by <code>-1</code>.</li>
</ul>

<p>Two elements are considered <strong>adjacent</strong> if and only if they share a <strong>border</strong>.</p>

<p>Your goal is to <strong>maximize</strong> the summation of the matrix&#39;s elements. Return <em>the <strong>maximum</strong> sum of the matrix&#39;s elements using the operation mentioned above.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/images/pc79-q2ex1.png" style="width: 401px; height: 81px;" />
<pre>
<strong>Input:</strong> matrix = [[1,-1],[-1,1]]
<strong>Output:</strong> 4
<b>Explanation:</b> We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/images/pc79-q2ex2.png" style="width: 321px; height: 121px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
<strong>Output:</strong> 16
<b>Explanation:</b> We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 250</code></li>
	<li><code>-10<sup>5</sup> &lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If there is a zero in the matrix, or the number of negative numbers in the matrix is even, then the maximum sum is the sum of the absolute values of all elements in the matrix.

Otherwise, if there are an odd number of negative numbers in the matrix, there will be one negative number left in the end. We choose the number with the smallest absolute value and make it negative, so that the final sum is maximized.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxMatrixSum(self, matrix: List[List[int]]) -> int:
        mi = inf
        s = cnt = 0
        for row in matrix:
            for x in row:
                cnt += x < 0
                y = abs(x)
                mi = min(mi, y)
                s += y
        return s if cnt % 2 == 0 else s - mi * 2
```

#### Java

```java
class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long s = 0;
        int mi = 1 << 30, cnt = 0;
        for (var row : matrix) {
            for (int x : row) {
                cnt += x < 0 ? 1 : 0;
                int y = Math.abs(x);
                mi = Math.min(mi, y);
                s += y;
            }
        }
        return cnt % 2 == 0 ? s : s - mi * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxMatrixSum(vector<vector<int>>& matrix) {
        long long s = 0;
        int mi = 1 << 30, cnt = 0;
        for (const auto& row : matrix) {
            for (int x : row) {
                cnt += x < 0 ? 1 : 0;
                int y = abs(x);
                mi = min(mi, y);
                s += y;
            }
        }
        return cnt % 2 == 0 ? s : s - mi * 2;
    }
};
```

#### Go

```go
func maxMatrixSum(matrix [][]int) int64 {
	var s int64
	mi, cnt := 1<<30, 0
	for _, row := range matrix {
		for _, x := range row {
			if x < 0 {
				cnt++
				x = -x
			}
			mi = min(mi, x)
			s += int64(x)
		}
	}
	if cnt%2 == 0 {
		return s
	}
	return s - int64(mi*2)
}
```

#### TypeScript

```ts
function maxMatrixSum(matrix: number[][]): number {
    let [s, cnt, mi] = [0, 0, Infinity];
    for (const row of matrix) {
        for (const x of row) {
            if (x < 0) {
                ++cnt;
            }
            const y = Math.abs(x);
            s += y;
            mi = Math.min(mi, y);
        }
    }
    return cnt % 2 === 0 ? s : s - 2 * mi;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_matrix_sum(matrix: Vec<Vec<i32>>) -> i64 {
        let mut s = 0;
        let mut mi = i32::MAX;
        let mut cnt = 0;
        for row in matrix {
            for &x in row.iter() {
                cnt += if x < 0 { 1 } else { 0 };
                let y = x.abs();
                mi = mi.min(y);
                s += y as i64;
            }
        }
        if cnt % 2 == 0 {
            s
        } else {
            s - (mi as i64 * 2)
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {number}
 */
var maxMatrixSum = function (matrix) {
    let [s, cnt, mi] = [0, 0, Infinity];
    for (const row of matrix) {
        for (const x of row) {
            if (x < 0) {
                ++cnt;
            }
            const y = Math.abs(x);
            s += y;
            mi = Math.min(mi, y);
        }
    }
    return cnt % 2 === 0 ? s : s - 2 * mi;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

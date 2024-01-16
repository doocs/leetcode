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

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum path sum from the bottom of the triangle to the position $(i, j)$. Here, the position $(i, j)$ refers to the position in the $i$th row and $j$th column of the triangle (both starting from $0$). Then we have the following state transition equation:

$$
f[i][j] = \min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j]
$$

The answer is $f[0][0]$.

We notice that the state $f[i][j]$ is only related to the states $f[i + 1][j]$ and $f[i + 1][j + 1]$, so we can use a one-dimensional array instead of a two-dimensional array, reducing the space complexity from $O(n^2)$ to $O(n)$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the number of rows in the triangle.

Furthermore, we can directly reuse the `triangle` as the `f` array, so there is no need to create an additional `f` array, reducing the space complexity to $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        f = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                f[i][j] = min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j]
        return f[0][0]
```

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                f[j] = Math.min(f[j], f[j + 1]) + triangle.get(i).get(j);
            }
        }
        return f[0];
    }
}
```

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            for (int j = 0; j <= i; ++j) {
                f[j] = min(f[j], f[j + 1]) + triangle[i][j];
            }
        }
        return f[0];
    }
};
```

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			f[j] = min(f[j], f[j+1]) + triangle[i][j]
		}
	}
	return f[0]
}
```

```ts
function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; ~i; --i) {
        for (let j = 0; j <= i; ++j) {
            f[j] = Math.min(f[j], f[j + 1]) + triangle[i][j];
        }
    }
    return f[0];
}
```

```rust
impl Solution {
    pub fn minimum_total(triangle: Vec<Vec<i32>>) -> i32 {
        let n = triangle.len();
        let mut f = vec![0; n + 1];
        for i in (0..n).rev() {
            for j in 0..=i {
                f[j] = f[j].min(f[j + 1]) + triangle[i][j];
            }
        }
        f[0]
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                f[j] = min(f[j], f[j + 1]) + triangle[i][j]
        return f[0]
```

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                int x = triangle.get(i).get(j);
                int y = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                triangle.get(i).set(j, x + y);
            }
        }
        return triangle.get(0).get(0);
    }
}
```

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        for (int i = triangle.size() - 2; ~i; --i) {
            for (int j = 0; j <= i; ++j) {
                triangle[i][j] += min(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
};
```

```go
func minimumTotal(triangle [][]int) int {
	for i := len(triangle) - 2; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			triangle[i][j] += min(triangle[i+1][j], triangle[i+1][j+1])
		}
	}
	return triangle[0][0]
}
```

```ts
function minimumTotal(triangle: number[][]): number {
    for (let i = triangle.length - 2; ~i; --i) {
        for (let j = 0; j <= i; ++j) {
            triangle[i][j] += Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
        }
    }
    return triangle[0][0];
}
```

```rust
impl Solution {
    pub fn minimum_total(triangle: Vec<Vec<i32>>) -> i32 {
        let mut triangle = triangle;
        for i in (0..triangle.len() - 1).rev() {
            for j in 0..=i {
                triangle[i][j] += triangle[i + 1][j].min(triangle[i + 1][j + 1]);
            }
        }
        triangle[0][0]
    }
}
```

<!-- tabs:end -->

### Solution 3

<!-- tabs:start -->

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        for i in range(n - 2, -1, -1):
            for j in range(i + 1):
                triangle[i][j] = (
                    min(triangle[i + 1][j], triangle[i + 1][j + 1]) + triangle[i][j]
                )
        return triangle[0][0]
```

<!-- tabs:end -->

<!-- end -->

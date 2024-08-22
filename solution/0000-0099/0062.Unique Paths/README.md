---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0062.Unique%20Paths/README.md
tags:
    - 数学
    - 动态规划
    - 组合数学
---

<!-- problem:start -->

# [62. 不同路径](https://leetcode.cn/problems/unique-paths)

[English Version](/solution/0000-0099/0062.Unique%20Paths/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个机器人位于一个 <code>m x n</code><em>&nbsp;</em>网格的左上角 （起始点在下图中标记为 “Start” ）。</p>

<p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。</p>

<p>问总共有多少条不同的路径？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0062.Unique%20Paths/images/1697422740-adxmsI-image.png" style="width: 400px; height: 183px;" />
<pre>
<strong>输入：</strong>m = 3, n = 7
<strong>输出：</strong>28</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>m = 3, n = 2
<strong>输出：</strong>3
<strong>解释：</strong>
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -&gt; 向下 -&gt; 向下
2. 向下 -&gt; 向下 -&gt; 向右
3. 向下 -&gt; 向右 -&gt; 向下
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>m = 7, n = 3
<strong>输出：</strong>28
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>m = 3, n = 3
<strong>输出：</strong>6</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li>题目数据保证答案小于等于 <code>2 * 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示从左上角走到 $(i, j)$ 的路径数量，初始时 $f[0][0] = 1$，答案为 $f[m - 1][n - 1]$。

考虑 $f[i][j]$：

-   如果 $i \gt 0$，那么 $f[i][j]$ 可以从 $f[i - 1][j]$ 走一步到达，因此 $f[i][j] = f[i][j] + f[i - 1][j]$；
-   如果 $j \gt 0$，那么 $f[i][j]$ 可以从 $f[i][j - 1]$ 走一步到达，因此 $f[i][j] = f[i][j] + f[i][j - 1]$。

因此，我们有如下的状态转移方程：

$$
f[i][j] = \begin{cases}
1 & i = 0, j = 0 \\
f[i - 1][j] + f[i][j - 1] & \textit{otherwise}
\end{cases}
$$

最终的答案即为 $f[m - 1][n - 1]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

我们注意到 $f[i][j]$ 仅与 $f[i - 1][j]$ 和 $f[i][j - 1]$ 有关，因此我们优化掉第一维空间，仅保留第二维空间，得到时间复杂度 $O(m \times n)$，空间复杂度 $O(n)$ 的实现。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        f = [[0] * n for _ in range(m)]
        f[0][0] = 1
        for i in range(m):
            for j in range(n):
                if i:
                    f[i][j] += f[i - 1][j]
                if j:
                    f[i][j] += f[i][j - 1]
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int uniquePaths(int m, int n) {
        var f = new int[m][n];
        f[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    f[i][j] += f[i - 1][j];
                }
                if (j > 0) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> f(m, vector<int>(n));
        f[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i) {
                    f[i][j] += f[i - 1][j];
                }
                if (j) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
};
```

#### Go

```go
func uniquePaths(m int, n int) int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	f[0][0] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i > 0 {
				f[i][j] += f[i-1][j]
			}
			if j > 0 {
				f[i][j] += f[i][j-1]
			}
		}
	}
	return f[m-1][n-1]
}
```

#### TypeScript

```ts
function uniquePaths(m: number, n: number): number {
    const f: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    f[0][0] = 1;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i > 0) {
                f[i][j] += f[i - 1][j];
            }
            if (j > 0) {
                f[i][j] += f[i][j - 1];
            }
        }
    }
    return f[m - 1][n - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths(m: i32, n: i32) -> i32 {
        let (m, n) = (m as usize, n as usize);
        let mut f = vec![1; n];
        for i in 1..m {
            for j in 1..n {
                f[j] += f[j - 1];
            }
        }
        f[n - 1]
    }
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    const f = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    f[0][0] = 1;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i > 0) {
                f[i][j] += f[i - 1][j];
            }
            if (j > 0) {
                f[i][j] += f[i][j - 1];
            }
        }
    }
    return f[m - 1][n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        f = [[1] * n for _ in range(m)]
        for i in range(1, m):
            for j in range(1, n):
                f[i][j] = f[i - 1][j] + f[i][j - 1]
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int uniquePaths(int m, int n) {
        var f = new int[m][n];
        for (var g : f) {
            Arrays.fill(g, 1);
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> f(m, vector<int>(n, 1));
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
};
```

#### Go

```go
func uniquePaths(m int, n int) int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = 1
		}
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			f[i][j] = f[i-1][j] + f[i][j-1]
		}
	}
	return f[m-1][n-1]
}
```

#### TypeScript

```ts
function uniquePaths(m: number, n: number): number {
    const f: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1));
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    const f = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1));
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        f = [1] * n
        for _ in range(1, m):
            for j in range(1, n):
                f[j] += f[j - 1]
        return f[-1]
```

#### Java

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[j] += f[j - 1];
            }
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<int> f(n, 1);
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[j] += f[j - 1];
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func uniquePaths(m int, n int) int {
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			f[j] += f[j-1]
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function uniquePaths(m: number, n: number): number {
    const f: number[] = Array(n).fill(1);
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[j] += f[j - 1];
        }
    }
    return f[n - 1];
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    const f = Array(n).fill(1);
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[j] += f[j - 1];
        }
    }
    return f[n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9813.%20%E6%9C%BA%E5%99%A8%E4%BA%BA%E7%9A%84%E8%BF%90%E5%8A%A8%E8%8C%83%E5%9B%B4/README.md
---

<!-- problem:start -->

# [面试题 13. 机器人的运动范围](https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

## 题目描述

<!-- description:start -->

<p>地上有一个m行n列的方格，从坐标 <code>[0,0]</code> 到坐标 <code>[m-1,n-1]</code> 。一个机器人从坐标 <code>[0, 0] </code>的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>m = 2, n = 3, k = 1
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>m = 3, n = 1, k = 0
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n,m &lt;= 100</code></li>
	<li><code>0 &lt;= k&nbsp;&lt;= 20</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 哈希表

由于部分单元格不可达，因此，我们不能直接枚举所有坐标点 $(i, j)$ 进行判断，而是应该从起点 $(0, 0)$ 出发，搜索所有可达的节点，记录答案。

过程中，为了避免重复搜索同一个单元格，我们可以使用数组或哈希表记录所有访问过的节点。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为方格的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def f(x: int) -> int:
            return x // 10 + x % 10

        def dfs(i, j):
            if i >= m or j >= n or f(i) + f(j) > k or (i, j) in vis:
                return 0
            vis.add((i, j))
            return 1 + dfs(i + 1, j) + dfs(i, j + 1)

        vis = set()
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private boolean[][] vis;
    private int m;
    private int n;
    private int k;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        vis = new boolean[m][n];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= n || vis[i][j] || (i % 10 + i / 10 + j % 10 + j / 10) > k) {
            return 0;
        }
        vis[i][j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int movingCount(int m, int n, int k) {
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        auto f = [](int x) {
            return x / 10 + x % 10;
        };
        function<int(int i, int j)> dfs = [&](int i, int j) -> int {
            if (i < 0 || i >= m || j < 0 || j >= n || vis[i][j] || f(i) + f(j) > k) {
                return false;
            }
            vis[i][j] = true;
            return 1 + dfs(i + 1, j) + dfs(i, j + 1);
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func movingCount(m int, n int, k int) int {
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= n || vis[i][j] || (i%10+i/10+j%10+j/10) > k {
			return 0
		}
		vis[i][j] = true
		return 1 + dfs(i+1, j) + dfs(i, j+1)
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function movingCount(m: number, n: number, k: number): number {
    const vis: boolean[] = Array(m * n).fill(false);
    const f = (x: number): number => {
        return ((x / 10) | 0) + (x % 10);
    };
    const dfs = (i: number, j: number): number => {
        if (i >= m || j >= n || vis[i * n + j] || f(i) + f(j) > k) {
            return 0;
        }
        vis[i * n + j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    };
    return dfs(0, 0);
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var movingCount = function (m, n, k) {
    const vis = Array(m * n).fill(false);
    const f = x => {
        return ((x / 10) | 0) + (x % 10);
    };
    const dfs = (i, j) => {
        if (i >= m || j >= n || vis[i * n + j] || f(i) + f(j) > k) {
            return 0;
        }
        vis[i * n + j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    };
    return dfs(0, 0);
};
```

#### C#

```cs
public class Solution {
    public int MovingCount(int m, int n, int k) {
        bool[,] arr = new bool[m, n];
        return dfs(0, 0, m, n, k, arr);
    }

    public int dfs(int i, int j, int m, int n, int k, bool[,] arr) {
        if (i >= m || j >= n || arr[i,j] || (i % 10 + j % 10 + i / 10 + j / 10) > k) {
            return 0;
        }
        arr[i,j] = true;
        return 1 + dfs(i+1, j, m, n, k, arr) + dfs(i, j+1, m, n, k, arr);
    }
}
```

#### Swift

```swift
class Solution {
    private var vis: [[Bool]] = []
    private var m: Int = 0
    private var n: Int = 0
    private var k: Int = 0

    func movingCount(_ m: Int, _ n: Int, _ k: Int) -> Int {
        self.m = m
        self.n = n
        self.k = k
        self.vis = Array(repeating: Array(repeating: false, count: n), count: m)
        return dfs(0, 0)
    }

    private func dfs(_ i: Int, _ j: Int) -> Int {
        if i >= m || j >= n || vis[i][j] || (digitSum(i) + digitSum(j)) > k {
            return 0
        }
        vis[i][j] = true
        return 1 + dfs(i + 1, j) + dfs(i, j + 1)
    }

    private func digitSum(_ num: Int) -> Int {
        var num = num
        var sum = 0
        while num > 0 {
            sum += num % 10
            num /= 10
        }
        return sum
    }
}

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

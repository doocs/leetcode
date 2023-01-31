# [面试题 13. 机器人的运动范围](https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

## 题目描述

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

## 解法

**方法一：DFS + 哈希表**

由于部分单元格不可达，因此，我们不能直接枚举所有坐标点 $(i, j)$ 进行判断，而是应该从起点 $(0, 0)$ 出发，搜索所有可达的节点，记录答案。

过程中，为了避免重复搜索同一个单元格，我们可以使用数组或哈希表记录所有访问过的节点。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为方格的行数和列数。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def f(x):
            s = 0
            while x:
                s += x % 10
                x //= 10
            return s

        def dfs(i, j):
            vis.add((i, j))
            nonlocal ans
            ans += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and f(x) + f(y) <= k and (x, y) not in vis:
                    dfs(x, y)

        vis = set()
        ans = 0
        dirs = (0, 1, 0)
        dfs(0, 0)
        return ans
```

```python
class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def f(x):
            s = 0
            while x:
                s += x % 10
                x //= 10
            return s

        def dfs(i, j):
            if not (0 <= i < m) or not (0 <= j < n) or f(i) + f(j) > k or (i, j) in vis:
                return 0
            vis.add((i, j))
            return 1 + dfs(i + 1, j) + dfs(i, j + 1)

        vis = set()
        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    private boolean[][] vis;
    private int m;
    private int n;
    private int k;
    private int ans;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        vis = new boolean[m][n];
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int j) {
        vis[i][j] = true;
        ++ans;
        int[] dirs = {1, 0, 1};
        for (int l = 0; l < 2; ++l) {
            int x = i + dirs[l], y = j + dirs[l + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && f(x) + f(y) <= k && !vis[x][y]) {
                dfs(x, y);
            }
        }
    }

    private int f(int x) {
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s;
    }
}
```

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

### **C++**

```cpp
class Solution {
public:
    int movingCount(int m, int n, int k) {
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        int ans = 0;
        int dirs[3] = {1, 0, 1};
        auto f = [](int x) {
            int s = 0;
            for (; x; x /= 10) {
                s += x % 10;
            }
            return s;
        };
        function<void(int i, int j)> dfs = [&](int i, int j) {
            vis[i][j] = true;
            ++ans;
            for (int l = 0; l < 2; ++l) {
                int x = i + dirs[l], y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && f(x) + f(y) <= k && !vis[x][y]) {
                    dfs(x, y);
                }
            }
        };
        dfs(0, 0);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int movingCount(int m, int n, int k) {
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        auto f = [](int x) {
            int s = 0;
            for (; x; x /= 10) {
                s += x % 10;
            }
            return s;
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

### **Go**

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

```go
func movingCount(m int, n int, k int) (ans int) {
	f := func(x int) (s int) {
		for ; x > 0; x /= 10 {
			s += x % 10
		}
		return
	}
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}

	dirs := [3]int{1, 0, 1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		vis[i][j] = true
		ans++
		for l := 0; l < 2; l++ {
			x, y := i+dirs[l], j+dirs[l+1]
			if x >= 0 && x < m && y >= 0 && y < n && f(x)+f(y) <= k && !vis[x][y] {
				dfs(x, y)
			}
		}
	}
	dfs(0, 0)
	return
}
```

### **JavaScript**

```js
/**
 * @param {number} m
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var movingCount = function (m, n, k) {
    const vis = new Array(m * n).fill(false);
    let dfs = function (i, j) {
        if (
            i >= m ||
            j >= n ||
            vis[i * n + j] ||
            (i % 10) + Math.floor(i / 10) + (j % 10) + Math.floor(j / 10) > k
        ) {
            return 0;
        }
        vis[i * n + j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    };
    return dfs(0, 0);
};
```

### **TypeScript**

```ts
function movingCount(m: number, n: number, k: number): number {
    const set = new Set();
    const dfs = (i: number, j: number) => {
        const key = `${i},${j}`;
        if (
            i === m ||
            j === n ||
            set.has(key) ||
            `${i}${j}`.split('').reduce((r, v) => r + Number(v), 0) > k
        ) {
            return;
        }
        set.add(key);
        dfs(i + 1, j);
        dfs(i, j + 1);
    };
    dfs(0, 0);
    return set.size;
}
```

### **Rust**

循环：

```rust
use std::collections::{HashSet, VecDeque};
impl Solution {
    pub fn moving_count(m: i32, n: i32, k: i32) -> i32 {
        let mut set = HashSet::new();
        let mut queue = VecDeque::new();
        queue.push_back([0, 0]);
        while let Some([i, j]) = queue.pop_front() {
            let key = format!("{},{}", i, j);
            if i == m
                || j == n
                || set.contains(&key)
                || k < format!("{}{}", i, j)
                    .chars()
                    .map(|c| c.to_string().parse::<i32>().unwrap())
                    .sum::<i32>()
            {
                continue;
            }
            set.insert(key);
            queue.push_back([i + 1, j]);
            queue.push_back([i, j + 1]);
        }
        set.len() as i32
    }
}
```

递归：

```rust
impl Solution {
    fn dfs(sign: &mut Vec<Vec<bool>>, k: usize, i: usize, j: usize) -> i32 {
        if i == sign.len()
            || j == sign[0].len()
            || sign[i][j]
            || j % 10 + j / 10 % 10 + i % 10 + i / 10 % 10 > k
        {
            return 0;
        }
        sign[i][j] = true;
        1 + Self::dfs(sign, k, i + 1, j) + Self::dfs(sign, k, i, j + 1)
    }

    pub fn moving_count(m: i32, n: i32, k: i32) -> i32 {
        let mut sign = vec![vec![false; n as usize]; m as usize];
        Self::dfs(&mut sign, k as usize, 0, 0)
    }
}
```

### **C#**

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

### **...**

```

```

<!-- tabs:end -->

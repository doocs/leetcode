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

此题一大误区是：遍历所有单元格，按照公式计算是否可进入，并记录可进入的方格数量。

因为部分方格在公式上属于可进入，但不在机器人运动范围当中，进入一方格的前提条件是能够抵达相邻方格当中。

而后，条件限制只能从 `(0,0)` 起步，对此，只需要关注方格的下方与右方即可。

**流程**：

1. `(0,0)` 开始。

2. 根据公式判断 `(i, j)` 是否可进入：

    - 可进入，并继续往右 `(i, j + 1)` 往下 `(i + 1, j)` 重新执行流程 2。
    - 不可进入，退出结算。

3. 计算可进入区域的数量，返回即可。

**剪枝**：

对于已进入的方格，需要防止多次进入，否则会导致指数级耗时。

在确定方格可进入后，给方格加上标记。判断一个方格可进入之前，先查看是否存在对应的标记，存在标记时及时退出。

记录方式不限数组与哈希表。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def dfs(i, j):
            if (
                i >= m
                or j >= n
                or vis[i][j]
                or (i % 10 + i // 10 + j % 10 + j // 10) > k
            ):
                return 0
            vis[i][j] = True
            return 1 + dfs(i + 1, j) + dfs(i, j + 1)

        vis = [[False] * n for _ in range(m)]
        return dfs(0, 0)
```

### **Java**

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

### **C++**

```cpp
class Solution {
public:
    int m;
    int n;
    int k;
    vector<vector<bool>> vis;

    int movingCount(int m, int n, int k) {
        this->m = m;
        this->n = n;
        this->k = k;
        vis.resize(m, vector<bool>(n, false));
        return dfs(0, 0);
    }

    int dfs(int i, int j) {
        if (i >= m || j >= n || vis[i][j] || (i % 10 + i / 10 + j % 10 + j / 10) > k) return 0;
        vis[i][j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    }
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

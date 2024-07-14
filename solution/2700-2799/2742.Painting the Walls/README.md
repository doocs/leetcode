---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2742.Painting%20the%20Walls/README.md
rating: 2424
source: 第 350 场周赛 Q4
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2742. 给墙壁刷油漆](https://leetcode.cn/problems/painting-the-walls)

[English Version](/solution/2700-2799/2742.Painting%20the%20Walls/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>cost</code> 和&nbsp;<code>time</code>&nbsp;，分别表示给&nbsp;<code>n</code>&nbsp;堵不同的墙刷油漆需要的开销和时间。你有两名油漆匠：</p>

<ul>
	<li>一位需要 <strong>付费</strong>&nbsp;的油漆匠，刷第&nbsp;<code>i</code>&nbsp;堵墙需要花费&nbsp;<code>time[i]</code>&nbsp;单位的时间，开销为&nbsp;<code>cost[i]</code>&nbsp;单位的钱。</li>
	<li>一位 <strong>免费</strong>&nbsp;的油漆匠，刷 <strong>任意</strong>&nbsp;一堵墙的时间为&nbsp;<code>1</code>&nbsp;单位，开销为&nbsp;<code>0</code>&nbsp;。但是必须在付费油漆匠&nbsp;<strong>工作</strong>&nbsp;时，免费油漆匠才会工作。</li>
</ul>

<p>请你返回刷完 <code>n</code>&nbsp;堵墙最少开销为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>cost = [1,2,3,2], time = [1,2,3,2]
<b>输出：</b>3
<strong>解释：</strong>下标为 0 和 1 的墙由付费油漆匠来刷，需要 3 单位时间。同时，免费油漆匠刷下标为 2 和 3 的墙，需要 2 单位时间，开销为 0 。总开销为 1 + 2 = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>cost = [2,3,4,2], time = [1,1,1,1]
<b>输出：</b>4
<b>解释：</b>下标为 0 和 3 的墙由付费油漆匠来刷，需要 2 单位时间。同时，免费油漆匠刷下标为 1 和 2 的墙，需要 2 单位时间，开销为 0 。总开销为 2 + 2 = 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= cost.length &lt;= 500</code></li>
	<li><code>cost.length == time.length</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= time[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们可以考虑每一堵墙是给付费油漆匠刷还是给免费油漆匠刷，设计一个函数 $dfs(i, j)$，表示从第 $i$ 堵墙开始，且当前剩余的免费油漆匠工作时间为 $j$ 时，刷完剩余所有墙壁的最小开销。那么答案为 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的计算过程如下：

-   如果 $n - i \le j$，表示剩余的墙壁不超过免费油漆匠的工作时间，那么剩余的墙壁都由免费油漆匠刷，开销为 $0$；
-   如果 $i \ge n$，返回 $+\infty$；
-   否则，如果第 $i$ 堵墙由付费油漆匠刷，开销为 $cost[i]$，那么 $dfs(i, j) = dfs(i + 1, j + time[i]) + cost[i]$；如果第 $i$ 堵墙由免费油漆匠刷，开销为 $0$，那么 $dfs(i, j) = dfs(i + 1, j - 1)$。

注意，参数 $j$ 可能小于 $0$，因此，在实际编码过程中，除了 $Python$ 语言外，我们对 $j$ 加上一个偏移量 $n$，使得 $j$ 的取值范围为 $[0, 2n]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def paintWalls(self, cost: List[int], time: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if n - i <= j:
                return 0
            if i >= n:
                return inf
            return min(dfs(i + 1, j + time[i]) + cost[i], dfs(i + 1, j - 1))

        n = len(cost)
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private int n;
    private int[] cost;
    private int[] time;
    private Integer[][] f;

    public int paintWalls(int[] cost, int[] time) {
        n = cost.length;
        this.cost = cost;
        this.time = time;
        f = new Integer[n][n << 1 | 1];
        return dfs(0, n);
    }

    private int dfs(int i, int j) {
        if (n - i <= j - n) {
            return 0;
        }
        if (i >= n) {
            return 1 << 30;
        }
        if (f[i][j] == null) {
            f[i][j] = Math.min(dfs(i + 1, j + time[i]) + cost[i], dfs(i + 1, j - 1));
        }
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int paintWalls(vector<int>& cost, vector<int>& time) {
        int n = cost.size();
        int f[n][n << 1 | 1];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (n - i <= j - n) {
                return 0;
            }
            if (i >= n) {
                return 1 << 30;
            }
            if (f[i][j] == -1) {
                f[i][j] = min(dfs(i + 1, j + time[i]) + cost[i], dfs(i + 1, j - 1));
            }
            return f[i][j];
        };
        return dfs(0, n);
    }
};
```

#### Go

```go
func paintWalls(cost []int, time []int) int {
	n := len(cost)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n<<1|1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if n-i <= j-n {
			return 0
		}
		if i >= n {
			return 1 << 30
		}
		if f[i][j] == -1 {
			f[i][j] = min(dfs(i+1, j+time[i])+cost[i], dfs(i+1, j-1))
		}
		return f[i][j]
	}
	return dfs(0, n)
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn paint_walls(cost: Vec<i32>, time: Vec<i32>) -> i32 {
        let n = cost.len();
        let mut record_vec: Vec<Vec<i32>> = vec![vec![-1; n << 1 | 1]; n];
        Self::dfs(&mut record_vec, 0, n as i32, n as i32, &time, &cost)
    }

    #[allow(dead_code)]
    fn dfs(
        record_vec: &mut Vec<Vec<i32>>,
        i: i32,
        j: i32,
        n: i32,
        time: &Vec<i32>,
        cost: &Vec<i32>,
    ) -> i32 {
        if n - i <= j - n {
            // All the remaining walls can be printed at no cost
            // Just return 0
            return 0;
        }
        if i >= n {
            // No way this case can be achieved
            // Just return +INF
            return 1 << 30;
        }
        if record_vec[i as usize][j as usize] == -1 {
            // This record hasn't been written
            record_vec[i as usize][j as usize] = std::cmp::min(
                Self::dfs(record_vec, i + 1, j + time[i as usize], n, time, cost)
                    + cost[i as usize],
                Self::dfs(record_vec, i + 1, j - 1, n, time, cost),
            );
        }
        record_vec[i as usize][j as usize]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
